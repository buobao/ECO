package com.joint.turman.customwidget.textview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.joint.turman.customwidget.R;
import com.joint.turman.customwidget.textview.adapter.TextWatcherAdapter;

/**
 * Created by dqf on 2016/3/2.
 */
public class ClearableEditText extends AppCompatEditText implements View.OnTouchListener,
        View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {

    private OnTextClearListener onTextClearListener;

    public interface OnTextClearListener {
        void onTextClear();
    }

    public void setOnTextClearListener(OnTextClearListener onTextClearListener) {
        this.onTextClearListener = onTextClearListener;
    }

    private Drawable clearDrawable;
    private OnTouchListener onTouchListener;
    private OnFocusChangeListener onFocusChangeListener;

    public ClearableEditText(Context context) {
        super(context);
        initialise();
    }

    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialise();
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            boolean tappedX = event.getX() > (getWidth() - getPaddingRight() - clearDrawable
                    .getIntrinsicWidth());
            if (tappedX) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    setText("");
                    if (onTextClearListener != null) {
                        onTextClearListener.onTextClear();
                    }
                }
                return true;
            }
        }
        if (onTouchListener != null) {
            return onTouchListener.onTouch(v, event);
        }
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(!TextUtils.isEmpty(getText().toString()));
        } else {
            setClearIconVisible(false);
        }
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public void onTextChanged(EditText view, String text) {
        if (isFocused()) {
            setClearIconVisible(!TextUtils.isEmpty(text));
        }
    }

    private void initialise() {
        clearDrawable = getCompoundDrawables()[2];
        if (clearDrawable == null) {
            clearDrawable = getResources().getDrawable(getDefaultClearIconId());
        }
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcherAdapter(this, this));
    }

    private int getDefaultClearIconId() {
        return R.drawable.text_clear_selector;
    }

    protected void setClearIconVisible(boolean visible) {
        Drawable x = visible ? clearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], x, getCompoundDrawables()[3]);
    }

}
