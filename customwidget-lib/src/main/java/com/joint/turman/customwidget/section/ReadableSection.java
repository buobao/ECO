package com.joint.turman.customwidget.section;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joint.turman.customwidget.R;

/**
 * Created by dqf on 2016/3/22.
 */
public class ReadableSection extends LinearLayout {
    private LinearLayout mLayout;
    private TextView mLabel;
    private TextView mText;

    private OnTextClickListener mListener;

    public ReadableSection(Context context) {
        super(context);
        init(context, null);
    }

    public ReadableSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        View view = View.inflate(context, R.layout.widget_readable_section,null);
        mLayout = (LinearLayout) view.findViewById(R.id.readable_layout);
        mLabel = (TextView) view.findViewById(R.id.readable_label);
        mText = (TextView) view.findViewById(R.id.readable_text);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.readable_section);
        if (a != null) {
            int background = a.getColor(R.styleable.readable_section_layout_background, getResources().getColor(R.color.listitem_white));
            mLayout.setBackgroundColor(background);
            mLabel.setBackgroundColor(background);
            mText.setBackgroundColor(background);

            boolean isLink = a.getBoolean(R.styleable.readable_section_isLink, false);

            if (isLink) {
                Drawable[] drawables = mText.getCompoundDrawables();
                if (drawables[2] == null) {
                    drawables[2] = getResources().getDrawable(R.drawable.text_arrow);
                    mText.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
                }

                mText.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ReadableSection.this.mListener != null){
                            ReadableSection.this.mListener.onClick();
                        }
                    }
                });
            }

            int label_text_color = a.getColor(R.styleable.readable_section_label_text_color, getResources().getColor(R.color.colorGray));
            mLabel.setTextColor(label_text_color);
            float label_text_size = a.getDimension(R.styleable.readable_section_label_text_size, 16);
            mLabel.setTextSize(label_text_size);

            String text = a.getString(R.styleable.readable_section_label_text);
            mLabel.setText(text);

            int text_color = a.getColor(R.styleable.readable_section_text_color, getResources().getColor(R.color.listitem_black));
            mText.setTextColor(text_color);
            float text_size = a.getDimension(R.styleable.readable_section_text_size, 16);
            mText.setTextSize(text_size);
        }
        addView(view);
        if (a != null)
            a.recycle();
    }

    public interface OnTextClickListener{
        void onClick();
    }

    public void setOnTextListener(OnTextClickListener mListener) {
        this.mListener = mListener;
    }

    public void setLabelText(int str){
        mLabel.setText(str);
    }

    public void setLabelText(String str) {
        mLabel.setText(str);
    }

    public void setText(int str) {
        mText.setText(str);
    }

    public void setText(String str) {
        mText.setText(str);
    }

    public String getLabelText(){
        return mLabel.getText().toString();
    }

    public String getText(){
        return mText.getText().toString();
    }
}


















