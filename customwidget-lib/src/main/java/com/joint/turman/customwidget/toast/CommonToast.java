package com.joint.turman.customwidget.toast;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.joint.turman.customwidget.R;

/**
 * Created by dqf on 2016/3/7.
 */
public class CommonToast {
    public static final long DURATION_LONG = 5000L;
    public static final long DURATION_MEDIUM = 3500L;
    public static final long DURATION_SHORT = 2500L;
    private long _duration = 3500l;
    private ToastView _toastVw;
    private Context _context;

    public CommonToast(Activity activity) {
        init(activity);
    }

    public CommonToast(Activity activity, String message, int icon,
                       String action, int actionIcon, long l) {
        _duration = l;
        init(activity);
        setMessage(message);
        setMessageIc(icon);
        setAction(action);
        setActionIc(actionIcon);
    }

    private void init(Activity activity) {
        _context = activity;
        _toastVw = new ToastView(activity);
        setLayoutGravity(81);
    }

    public long getDuration() {
        return _duration;
    }

    public void setAction(String s) {
        _toastVw.actionTv.setText(s);
    }

    public void setActionIc(int i) {
        _toastVw.actionIv.setImageResource(i);
    }

    public void setDuration(long l) {
        _duration = l;
    }

    public void setLayoutGravity(int i) {
        if (i != 0) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2,
                    -2);
            params.gravity = i;
            DisplayMetrics displaymetrics = new DisplayMetrics();
            ((WindowManager)_context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);
            int j = (int) (16F * (displaymetrics.densityDpi / 160F));
            params.setMargins(j, j, j, j);
            _toastVw.setLayoutParams(params);
        }
    }

    public void setMessage(String s) {
        _toastVw.messageTv.setText(s);
    }

    public void setMessageIc(int i) {
        _toastVw.messageIc.setImageResource(i);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void show() {
        final ViewGroup content = (ViewGroup) ((Activity) _toastVw.getContext())
                .findViewById(android.R.id.content);
        if (content != null) {
            ObjectAnimator.ofFloat(_toastVw, "alpha", 0.0F).setDuration(0L)
                    .start();
            content.addView(_toastVw);
            ObjectAnimator.ofFloat(_toastVw, "alpha", 0.0F, 1.0F)
                    .setDuration(167L).start();
            _toastVw.postDelayed(new Runnable() {

                @Override
                public void run() {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(_toastVw,
                            "alpha", 1.0F, 0.0F);
                    animator.setDuration(100L);
                    animator.addListener(new Animator.AnimatorListener() {

                        @Override
                        public void onAnimationStart(Animator animation) {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            content.removeView(_toastVw);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }
                    });
                    animator.start();
                }
            }, _duration);
        } else {
            Log.e("custom-lib", "Toast not shown! Content view is null!");
        }
    }

    private class ToastView extends FrameLayout {

        public ImageView actionIv;
        public TextView actionTv;
        public ImageView messageIc;
        public TextView messageTv;

        public ToastView(Context context) {
            this(context, null);
        }

        public ToastView(Context context, AttributeSet attributeset) {
            this(context, attributeset, 0);
        }

        public ToastView(Context context, AttributeSet attributeset, int i) {
            super(context, attributeset, i);
            init();
        }

        private void init() {
            LayoutInflater.from(getContext()).inflate(
                    R.layout.widget_common_toast, this, true);
            messageTv = (TextView) findViewById(R.id.title_tv);
            messageIc = (ImageView) findViewById(R.id.icon_iv);
            actionTv = (TextView) findViewById(R.id.title_tv);
            actionIv = (ImageView) findViewById(R.id.icon_iv);
        }
    }
}
