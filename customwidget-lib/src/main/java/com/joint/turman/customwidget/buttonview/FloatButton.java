package com.joint.turman.customwidget.buttonview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import com.joint.turman.customwidget.FontAwesomeUtils;
import com.joint.turman.customwidget.R;

/**
 * Created by dqf on 2016/3/4.
 */
public class FloatButton extends Button {
    private String fa_icon;

    public FloatButton(Context context) {
        super(context);
        init(null);
    }

    public FloatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FloatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs,defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray localTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FontAwesomeText);
        try {
            if (localTypedArray != null) {
                fa_icon = localTypedArray.getString(R.styleable.FontAwesomeText_fa_icon);
            }
        } finally {
            if (localTypedArray != null) {
                localTypedArray.recycle();
            }
        }
        this.setText(fa_icon);
        this.setTypeface(FontAwesomeUtils.getFont(getContext()));
    }
}
