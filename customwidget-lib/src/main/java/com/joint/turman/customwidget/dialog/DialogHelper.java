package com.joint.turman.customwidget.dialog;

import android.app.Activity;

import com.joint.turman.customwidget.R;

/**
 * Created by Administrator on 2016/3/6.
 */
public class DialogHelper {

    public static WaitDialog getWaitDialog(Activity activity, int message) {
        WaitDialog dialog = null;
        try {
            dialog = new WaitDialog(activity, R.style.dialog_waiting);
            dialog.setMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }

    public static WaitDialog getWaitDialog(Activity activity, String message) {
        WaitDialog dialog = null;
        try {
            dialog = new WaitDialog(activity, R.style.dialog_waiting);
            dialog.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dialog;
    }

    public static WaitDialog getCancelableWaitDialog(Activity activity,
                                                     String message) {
        WaitDialog dialog = null;
        try {
            dialog = new WaitDialog(activity, R.style.dialog_waiting);
            dialog.setMessage(message);
            dialog.setCancelable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dialog;
    }
}
