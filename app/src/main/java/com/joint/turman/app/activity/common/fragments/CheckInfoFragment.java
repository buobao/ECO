package com.joint.turman.app.activity.common.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.fragments.lists.adapters.ProInfoAdapter;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.ProInfo;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.ProinfoListCallback;
import com.joint.turman.app.service.UserService;
import com.joint.turman.app.sys.TurmanApplication;
import com.joint.turman.app.ui.listview.SimpleListView;
import com.joint.turman.app.utils.DateUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/17.
 */
public class CheckInfoFragment extends Fragment {

    private MaterialCalendarView mCalendar;
    private SimpleListView mListView;
    private TextView mErrorMessage;

    private LinkedList<ProInfo> entityList;
    private ProInfoAdapter adapter;
    private int pageIndex = 1;
    private Map<String,Object> params;
    private boolean mIsAllLoaded = false;

    private Date mSelectDate = null;

    private int lastItemIndex;

    protected Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x01:
                    resetList();
                    adapter = new ProInfoAdapter(entityList, getActivity());
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
            }
        }
    };

    private ProinfoListCallback callback = new ProinfoListCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
            mhandler.sendEmptyMessage(0x05);
        }

        @Override
        public void onResponse(ListResult<ProInfo> response) {
            Status status = response.getResult();
            if (status.getErrorCode() == 1){
                ListEntity<ProInfo> result = response.getData();
                List<ProInfo> list = result.getList();
                if (list != null && list.size() > 0) {
                    if (entityList == null) {
                        entityList = (LinkedList<ProInfo>) list;
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

        mCalendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String str = date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDay();
                mSelectDate = DateUtils.getDayStart(str);
                reloadDate();
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
        return view;
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserService.getCalendarDate(params,callback);
            }
        }).start();
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

}









































