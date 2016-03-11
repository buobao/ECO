package com.joint.turman.app.ui.empty;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joint.turman.app.R;
import com.joint.turman.app.sys.TurmanApplication;

/**
 * Created by dqf on 2016/3/11.
 */
public class EmptyLayout extends LinearLayout implements View.OnClickListener {

    public static final int HIDE_LAYOUT = 0x01;
    public static final int NETWORK_ERROR = 0x02;
    public static final int NETWORK_LOADING = 0x04;
    public static final int NODATA = 0x08;
    public static final int NODATA_ENABLE_CLICK = 0x10;

    private ProgressBar animProgress;
    private boolean clickEnable = true;
    private Context context;
    private ImageView img;
    private OnClickListener listener;
    private int mErrorState;
    private RelativeLayout mLayout;
    private String strNoDataContent = "";
    private TextView tv;
    private TurmanApplication _app;

    public EmptyLayout(Context context) {
        super(context);
        this.context = context;
        this._app = ((TurmanApplication)context.getApplicationContext());
        init();
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this._app = ((TurmanApplication)context.getApplicationContext());
        init();
    }

    private void init() {
        View view = View.inflate(context, R.layout.view_empty,null);
        img = (ImageView) view.findViewById(R.id.empty_err_img);
        tv = (TextView) view.findViewById(R.id.empty_reload);
        mLayout = (RelativeLayout) view.findViewById(R.id.empty_err_layout);
        animProgress = (ProgressBar) view.findViewById(R.id.empty_progress);
        setBackgroundColor(-1);
        setOnClickListener(this);
        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickEnable) {
                    if (listener != null) {
                        listener.onClick(v);
                    }
                }
            }
        });
        addView(view);
        changeErrorLayoutBgMode(context);
    }

    private void changeErrorLayoutBgMode(Context context) {

    }

    public void dismiss(){
        mErrorState = HIDE_LAYOUT;
        setVisibility(View.GONE);
    }

    public int getErrorState(){
        return mErrorState;
    }

    public boolean isLoadError(){
        return mErrorState == NETWORK_ERROR;
    }

    public boolean isLoading(){
        return mErrorState == NETWORK_LOADING;
    }

    @Override
    public void onClick(View v) {
        if (clickEnable){
            if (listener != null){
                listener.onClick(v);
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        onSkinChanged();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void onSkinChanged() {

    }

    public void setErrorMessage(String msg) {
        tv.setText(msg);
    }

    public void setErrorType(int i){
        setVisibility(View.VISIBLE);
        switch (i){
            case NETWORK_ERROR:
                mErrorState = NETWORK_ERROR;
                if (_app.hasInternet()) {
                    tv.setText("内容加载失败\\r\\n点击重新加载");
                    img.setBackgroundResource(R.drawable.page_icon_failed);
                } else {
                    tv.setText("没有可用网络");
                    img.setBackgroundResource(R.drawable.page_icon_network);
                }
                img.setVisibility(View.VISIBLE);
                animProgress.setVisibility(View.GONE);
                clickEnable = true;
                break;
            case NETWORK_LOADING:
                mErrorState = NETWORK_LOADING;
                animProgress.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
                tv.setText("加载中...");
                clickEnable = false;
                break;
            case NODATA:
                mErrorState = NODATA;
                img.setBackgroundResource(R.drawable.page_icon_empty);
                img.setVisibility(View.VISIBLE);
                animProgress.setVisibility(View.GONE);
                setTvNoDataContent();
                clickEnable = true;
                break;
            case HIDE_LAYOUT:
                setVisibility(View.GONE);
                break;
            case NODATA_ENABLE_CLICK:
                mErrorState = NODATA_ENABLE_CLICK;
                img.setBackgroundResource(R.drawable.page_icon_empty);
                img.setVisibility(View.VISIBLE);
                animProgress.setVisibility(View.GONE);
                setTvNoDataContent();
                clickEnable = true;
                break;
            default:
                break;
        }
    }

    public void setOnLayoutClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void setTvNoDataContent() {
        if (!strNoDataContent.equals(""))
            tv.setText(strNoDataContent);
        else
            tv.setText("暂无内容\\r\\n点击刷新");
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == View.GONE)
            mErrorState = HIDE_LAYOUT;
        super.setVisibility(visibility);
    }

    public String getMessage() {
        if(tv != null) {
            return tv.getText().toString();
        }
        return "";
    }
}





























