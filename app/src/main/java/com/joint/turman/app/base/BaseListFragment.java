package com.joint.turman.app.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.joint.turman.app.R;
import com.joint.turman.app.entity.BaseEntity;
import com.joint.turman.app.ui.search.SearchBar;

import java.util.LinkedList;

/**
 * Created by dqf on 2016/3/11.
 */
public abstract class BaseListFragment<T extends BaseEntity, A extends ListAdapter> extends BaseFragment {
    public static int INIT_LOADING = 0x01;
    public static int PAGE_LOADING = 0x02;

    protected SearchBar mSearchBar;

    protected LinkedList<T> entityList = null;
    protected int pageIndex;
    protected A adapter;
    protected LinearLayout loading_layout;
    protected ListView mListView;
    protected TextView mErrorMessage;

    protected int lastItemIndex;

    protected Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x01:
                    adapter = getAdapter();
                    mListView.setAdapter(adapter);
                    hideLoading(entityList != null);
                    break;
                case 0x02:
                    adapter.notifyDataSetChanged();
                    break;
            }
            pageIndex++;
        }
    };

    protected A getAdapter(){
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        loading_layout = (LinearLayout) view.findViewById(R.id.frg_loading);
        mListView = (ListView) view.findViewById(R.id.frg_list);
        mErrorMessage = (TextView) view.findViewById(R.id.frm_error_message);
        pageIndex = 1;
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
                if (lastItemIndex == adapter.getCount() && scrollState == SCROLL_STATE_IDLE){
                    loadData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastItemIndex = firstVisibleItem + visibleItemCount;
            }
        });
        //初始化加载数据
        loadData();
        //显示加载布局
        showLoading();
    }

    protected void loadData() {

    }

    protected void showLoading(){
        mListView.setVisibility(View.GONE);
        loading_layout.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.GONE);
    }

    protected void hideLoading(boolean flag){
        loading_layout.setVisibility(View.GONE);
        if (flag) {
            mListView.setVisibility(View.VISIBLE);
        } else {
            mErrorMessage.setVisibility(View.VISIBLE);
        }
    }
}
