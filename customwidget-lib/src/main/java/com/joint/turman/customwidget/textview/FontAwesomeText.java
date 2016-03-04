package com.joint.turman.customwidget.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.joint.turman.customwidget.FontAwesomeUtils;
import com.joint.turman.customwidget.R;

/**
 * Created by dqf on 2016/3/3.
 */
public class FontAwesomeText extends TextView {

    private String fa_icon;

    public FontAwesomeText(Context context) {
        super(context);
        initialise(null);
    }

    public FontAwesomeText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise(attrs);
    }

    public FontAwesomeText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialise(attrs);
    }

    private void initialise(AttributeSet attrs) {
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

        if (!isInEditMode()) {
            if (!TextUtils.isEmpty(fa_icon)) {
                this.setText(fa_icon);
            }
            this.setTypeface(FontAwesomeUtils.getFont(getContext()));
        }
    }

}
