package com.joint.turman.app.ui.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.joint.turman.app.R;

/**
 * Created by dqf on 2016/3/15.
 */
public class SimpleListView extends ListView {

    public static final int LOADING = 0x01;
    public static final int LOADING_MORE = 0x02;
    public static final int LOADING_NO_MORE = 0x04;

    private LinearLayout mFoot;
    private TextView mFootText;
    private ProgressBar mFootProgress;

//    private LinearLayout mHead;

    public SimpleListView(Context context) {
        super(context);
        initView();
    }

    public SimpleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        mFoot = (LinearLayout) View.inflate(getContext(), R.layout.listview_foot, null);
        this.addFooterView(mFoot);
        mFootText = (TextView) mFoot.findViewById(R.id.listview_foot_load_more);
        mFootProgress = (ProgressBar) mFoot.findViewById(R.id.listview_foot_loading);

//        mHead = (LinearLayout) View.inflate(getContext(), R.layout.listview_head, null);
//        this.addHeaderView(mHead);
    }

    public void resetFoot(){
        updateFoot(LOADING_MORE);
    }

    public void updateFoot(int flag){
        switch (flag) {
            case LOADING:
                mFootText.setVisibility(GONE);
                mFootProgress.setVisibility(VISIBLE);
                break;
            case LOADING_MORE:
                mFootText.setText("加载更多");
                mFootText.setVisibility(VISIBLE);
                mFootProgress.setVisibility(GONE);
                break;
            case LOADING_NO_MORE:
                mFootText.setText("没有更多");
                mFootText.setVisibility(VISIBLE);
                mFootProgress.setVisibility(GONE);
                break;
        }
    }
}
