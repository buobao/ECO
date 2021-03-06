package com.joint.turman.app.activity.common.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.ContentEnum;
import com.joint.turman.app.activity.common.fragments.lists.adapters.ProSignAdapter;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.ProSign;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.ProSignListCallback;
import com.joint.turman.app.internate.callback.Callback;
import com.joint.turman.app.service.UserService;
import com.joint.turman.app.sys.TurmanApplication;
import com.joint.turman.app.ui.listview.SimpleListView;
import com.joint.turman.app.utils.DateUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dqf on 2016/3/17.
 */
public class CheckInfoFragment extends Fragment {

    private MaterialCalendarView mCalendar;
    private SimpleListView mListView;
    private TextView mErrorMessage;

    private LinkedList<ProSign> entityList;
    private ProSignAdapter adapter;
    private int pageIndex = 1;
    private Map<String,Object> params;
    private boolean mIsAllLoaded = false;

    private Date mSelectDate = null;

    private Date mMonthStart = null;
    private Date mMonthEnd = null;

    private int lastItemIndex;

    protected Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x01:
                    resetList();
                    adapter = new ProSignAdapter(entityList, getActivity());
                    mListView.setAdapter(adapter);
                    pageIndex++;
                    break;
                case 0x02:
                    adapter.notifyDataSetChanged();
                    mListView.updateFoot(SimpleListView.LOADING_MORE);
                    pageIndex++;
                    break;
                case 0x03:
                    mListView.updateFoot(SimpleListView.LOADING_NO_MORE);
                    mIsAllLoaded = true;
                    break;
                case 0x04:
                    dataLoadError("没有数据");
                    mIsAllLoaded = true;
                    break;
                case 0x05:
                    dataLoadError("网络异常,请联系管理员");
                    break;
                case 0x06:
                    //setDecorator();
                    Bundle bundle = msg.getData();
                    setDecorator((List<String>)bundle.getParcelableArrayList("list").get(0));
                    break;
            }
        }
    };

    private Callback<List<String>> datelist_callback = new Callback<List<String>>() {
        @Override
        public List<String> parseNetworkResponse(Response response) throws Exception {
            String json_str = response.body().string();
            JsonObject obj = new JsonParser().parse(json_str).getAsJsonObject();
            JsonObject data = obj.get("data").getAsJsonObject();
            JsonArray arr =  data.get("dateList").getAsJsonArray();

            List<String> result = new ArrayList<>();
            Iterator it = arr.iterator();
            while (it.hasNext()){
                JsonElement e = (JsonElement) it.next();
                result.add(_g.fromJson(e,String.class));
            }

            return result;
        }

        @Override
        public void onError(Call call, Exception e) {

        }

        @Override
        public void onResponse(List<String> response) {
            if (response.size() > 0) {
                Message msg = new Message();
                msg.what = 0x06;
                Bundle bundle = new Bundle();
                ArrayList list = new ArrayList();
                list .add(response);
                bundle.putParcelableArrayList("list", list);
                msg.setData(bundle);

                mhandler.sendMessage(msg);
            }
        }
    };

    private ProSignListCallback callback = new ProSignListCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
            mhandler.sendEmptyMessage(0x05);
        }

        @Override
        public void onResponse(ListResult<ProSign> response) {
            Status status = response.getResult();
            if (status.getErrorCode() == 1){
                ListEntity<ProSign> result = response.getData();
                List<ProSign> list = result.getList();
                if (list != null && list.size() > 0) {
                    if (entityList == null) {
                        entityList = (LinkedList<ProSign>) list;
                        mhandler.sendEmptyMessage(0x01);
                        if (list.size() < TurmanApplication.getPageSize()){
                            mhandler.sendEmptyMessage(0x03);
                        }
                    } else {
                        entityList.addAll(list);
                        if (list.size() < TurmanApplication.getPageSize()){
                            mhandler.sendEmptyMessage(0x03);
                        } else {
                            mhandler.sendEmptyMessage(0x02);
                        }
                    }
                } else {
                    if (entityList== null || entityList.size() == 0){
                        mhandler.sendEmptyMessage(0x04);
                    } else {
                        mhandler.sendEmptyMessage(0x03);
                    }

                }
            } else {
                mhandler.sendEmptyMessage(0x04);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_checkinfo,container,false);
        //init view
        mCalendar = (MaterialCalendarView) view.findViewById(R.id.frg_checkinfo_calendar);
        mListView = (SimpleListView) view.findViewById(R.id.frg_checkinfo_list);
        mErrorMessage = (TextView) view.findViewById(R.id.frg_checkinfo_loaderror);

        mMonthStart = DateUtils.getMonthFirstDay(new Date());
        mMonthEnd = DateUtils.getMonthLastDay(new Date());

        mCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String str = date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDay();
                mSelectDate = DateUtils.getDayStart(str);
                reloadDate();
            }
        });

        mCalendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                mMonthStart = DateUtils.getMonthFirstDay(date.getDate());
                mMonthEnd = DateUtils.getMonthLastDay(date.getDate());
                loadDecorator();
            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (lastItemIndex == adapter.getCount() && scrollState == SCROLL_STATE_IDLE) {
                    loadData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastItemIndex = firstVisibleItem + visibleItemCount - 1;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String entityId = entityList.get(position).getId();
                String entityType = entityList.get(position).getType();

                ContentEnum tp = null;
                if ("Signin".equals(entityType)){
                    tp = ContentEnum.PROSIGN_DETAIL;
                }

                if ("Leave".equals(entityType)){
                    tp = ContentEnum.PROLEAVE_DETAIL;
                }
                if (tp != null) {
                    Bundle bundle = TurmanApplication.getContentBundle(tp, entityId);
                    TurmanApplication.openCommonActivity(getContext(), bundle);
                }
            }
        });

        loadDecorator();
        return view;
    }

    private void setDecorator(List<String> list){
        List<CalendarDay> calendarDayList = new ArrayList<>();
        for (String l:list){
            calendarDayList.add(CalendarDay.from(DateUtils.parse(l)));
        }
        mCalendar.addDecorator(new EventDecorator(Color.GRAY, calendarDayList));
    }

    private void loadDecorator(){
        Map<String,Object> dataList_params = new HashMap<String, Object>();
        dataList_params.put("_startTime", mMonthStart.getTime());
        dataList_params.put("_endTime", DateUtils.tomorrow(mMonthEnd).getTime());
        //dataList_params.put("proinfoId","");
        UserService.getCalendarDateList(dataList_params, datelist_callback);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){
        if (mIsAllLoaded){
            return;
        }

        if (params == null){
            params = new HashMap<>();
            params.put("pageSize", TurmanApplication.getPageSize());
        }
        params.put("pageIndex",pageIndex);
        if (mSelectDate != null){
            params.put("_startTime",mSelectDate.getTime());
            params.put("_endTime",DateUtils.getDayEnd(mSelectDate).getTime());
        } else {
            params.put("_startTime",DateUtils.getDayStart(new Date()).getTime());
            params.put("_endTime",DateUtils.getDayEnd(new Date()).getTime());
        }

        UserService.getCalendarDate(params, callback);
    }

    private void reloadDate(){
        entityList = null;
        pageIndex = 1;
        params = null;
        mIsAllLoaded = false;
        loadData();
    }

    private void resetList(){
        mListView.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.GONE);
    }

    private void dataLoadError(String msg){
        if (!"".equals(msg)){
            mErrorMessage.setText(msg);
        }
        mErrorMessage.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
    }

    public class EventDecorator implements DayViewDecorator {
        private int color;
        private HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(5, color));
        }
    }

}









































