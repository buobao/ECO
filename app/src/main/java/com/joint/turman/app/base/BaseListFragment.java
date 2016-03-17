package com.joint.turman.app.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joint.turman.app.R;
import com.joint.turman.app.bean.ParamsMap;
import com.joint.turman.app.entity.BaseEntity;
import com.joint.turman.app.sys.TurmanApplication;
import com.joint.turman.app.ui.listview.SimpleListView;
import com.joint.turman.app.ui.search.SearchBar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by dqf on 2016/3/11.
 */
public abstract class BaseListFragment<T extends BaseEntity, A extends ListAdapter> extends BaseFragment{
    public static final String DEFAULT_PARAMS = "default_params";

    private Map<String, Object> def_params = null;

    public static int INIT_LOADING = 0x01;
    public static int PAGE_LOADING = 0x02;
    public static int NO_LOADING = 0x03;

    protected Map<String, Object> params;

    protected SearchBar mSearchBar;

    protected LinkedList<T> entityList = null;
    protected int pageIndex;
    protected A adapter;

    protected SwipeRefreshLayout mRefreshLayout;
    protected LinearLayout loading_layout;
    protected SimpleListView mListView;
    protected TextView mErrorMessage;

    protected boolean isReload = false;
    protected int lastItemIndex;

    protected boolean allLoaded = false;

    protected Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x01:
                    adapter = getAdapter();
                    mListView.setAdapter(adapter);
                    if (!isReload) {
                        hideLoading(entityList != null);
                    } else {
                        mRefreshLayout.setRefreshing(false);
                        isReload = false;
                    }
                    pageIndex++;
                    break;
                case 0x02:
                    adapter.notifyDataSetChanged();
                    mListView.updateFoot(SimpleListView.LOADING_MORE);
                    pageIndex++;
                    break;
                case 0x03:
                    mListView.updateFoot(SimpleListView.LOADING_NO_MORE);
                    allLoaded = true;
                    break;
            }
        }
    };

    protected abstract A getAdapter();
    protected void loadData(){
        //如果所有数据都读取完成，则直接返回不发送数据读取请求
        //这里避免因为服务端刷新数据（增加或删除）导致最后一页重复读取数据
        //如需重新读取刷新后的数据列表则需要刷新list重新初始化加载数据
        if (allLoaded){
            return;
        }

        //初始查询条件
        if (params == null)
            params = new HashMap<String, Object>();
        params.put("pageIndex",pageIndex);
        params.put("pageSize", TurmanApplication.getPageSize());
        //添加默认参数
        if (def_params != null){
            for (String key:def_params.keySet()){
                params.put(key,def_params.get(key));
            }
        }
        if (pageIndex > 1){
            mListView.updateFoot(SimpleListView.LOADING);
        } else {
            if (!isReload) {
                //显示加载布局
                showLoading();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        mSearchBar = (SearchBar) view.findViewById(R.id.frg_search);
        if (!showSearchBar()){
            mSearchBar.setVisibility(View.GONE);
        }
        loading_layout = (LinearLayout) view.findViewById(R.id.frg_loading);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.frg_refresh_layout);
        mListView = (SimpleListView) view.findViewById(R.id.frg_list);
        mErrorMessage = (TextView) view.findViewById(R.id.frm_error_message);
        pageIndex = 1;

        //默认的查询参数，来自activity
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getSerializable(DEFAULT_PARAMS) != null) {
            def_params = ((ParamsMap) bundle.getSerializable(DEFAULT_PARAMS)).getMap();
        }

        initViews(view);
        return view;
    }

    protected int getLayout(){
        return R.layout.frg_base_list;
    }

    protected void initViews(View view){
        setHasOptionsMenu(true);
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

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                entityList = null;
                pageIndex = 1;
                isReload = true;
                allLoaded = false;
                mListView.resetFoot();
                loadData();
            }
        });

        //初始化加载数据
        loadData();
    }

    protected void showLoading(){
        mRefreshLayout.setVisibility(View.GONE);
        loading_layout.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.GONE);
    }

    protected void hideLoading(boolean flag){
        loading_layout.setVisibility(View.GONE);
        if (flag) {
            mRefreshLayout.setVisibility(View.VISIBLE);
        } else {
            mErrorMessage.setVisibility(View.VISIBLE);
        }
    }

    public boolean showSearchBar(){
        return true;
    }

}
