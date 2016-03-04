package com.joint.turman.customwidget.bottomtab;

import android.widget.Checkable;

/**
 * Created by dqf on 2016/3/4.
 */
public interface BottomTabImpl extends Checkable {

    interface OnCheckedChangeListener{
        void onCheckedChanged(BottomTabImpl buttonView, boolean isChecked);
    }

    int getId();

    void setOnCheckedChangeWidgetListener(OnCheckedChangeListener listener);
}
