package com.joint.turman.customwidget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.joint.turman.customwidget.R;

/**
 * Created by Administrator on 2016/3/5.
 */
public class WaitDialog extends Dialog {
    private TextView _messageTv;

    public WaitDialog(Context context) {
        super(context);
        init(context);
    }

    public WaitDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected WaitDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        setCancelable(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(R.layout.widget_wait_dialog,null);
        _messageTv = (TextView) view.findViewById(R.id.waiting_tv);
        setContentView(view);
    }

    public static void show(Context context) {
        if (context instanceof DialogControl)
            ((DialogControl) context).showWaitDialog();
    }

    public static boolean show(WaitDialog waitdialog) {
        boolean flag;
        if (waitdialog != null) {
            waitdialog.show();
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if ((0xf & getContext().getResources().getConfiguration().screenLayout) >= 3) {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            ((WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);
            int i = (int) (360F * (displaymetrics.densityDpi / 160F));
            if (i < displaymetrics.widthPixels) {
                WindowManager.LayoutParams params = getWindow()
                        .getAttributes();
                params.width = i;
                getWindow().setAttributes(params);
            }
        }
    }

    public void setMessage(int message) {
        _messageTv.setText(message);
    }

    public void setMessage(String message) {
        _messageTv.setText(message);
    }
}
