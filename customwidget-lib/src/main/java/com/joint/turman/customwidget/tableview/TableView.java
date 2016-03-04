package com.joint.turman.customwidget.tableview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joint.turman.customwidget.R;
import com.joint.turman.customwidget.textview.FontAwesomeText;

/**
 * Created by dqf on 2016/3/3.
 */
public class TableView extends RelativeLayout implements View.OnClickListener {

    private ImageView mIvTableArrow;//右箭头图标
    private ImageView mIvTableIconImage;//左图标
    private FontAwesomeText mTvTableIconFont;//左图标
    private TextView mTvTableLeftText;//左文字
    private TextView mTvTableLeftText_2;//左文字2
    private TextView mTvTableLeftText_3;//左文字3
    private TextView mTvTableRightText;//右文字
    private ImageView mIvTableRightImage;//右图标
    private ImageView mIvTableIndicator;//小红点
    private SwitchCompat mTbTableToggleButton;//开关

    private boolean isChangeBackgroud;
    private boolean isShowArrow;//是否显示右箭头图标
    private Drawable iconImage;
    private boolean isIconLargeSize;
    private float iconImageWidth;
    private float iconImageHeight;
    private String iconFont;
    private int iconFontColor;
    private String leftText;
    private String leftText_2;
    private String leftText_3;
    private String rightText;
    private Drawable rightImage;
    private boolean isShowTogglebutton;

    public TableView(Context context) {
        super(context);
        initialise(null);
    }

    public TableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise(attrs);
    }

    public TableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialise(attrs);
    }

    private void initialise(AttributeSet attrs) {
        int space16 = getResources().getDimensionPixelSize(R.dimen.space_16);
        int space12 = getResources().getDimensionPixelSize(R.dimen.space_12);
        setPadding(space16, space12, space16, space12);
        LayoutInflater.from(getContext()).inflate(R.layout.widget_table_view, this, true);
        TypedArray localTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.tableView);
        try {
            if (localTypedArray != null) {
                isChangeBackgroud = localTypedArray.getBoolean(R.styleable.tableView_table_backgroud_change, true);//默认可点击
                isShowArrow = localTypedArray.getBoolean(R.styleable.tableView_show_arrow, true);//默认显示右箭头图标
                iconImage = localTypedArray.getDrawable(R.styleable.tableView_icon_image);
                isIconLargeSize = localTypedArray.getBoolean(R.styleable.tableView_icon_largeSize, false);//默认不大图标
                iconImageWidth = localTypedArray.getDimension(R.styleable.tableView_icon_imageWidth, 29.0F);
                iconImageHeight = localTypedArray.getDimension(R.styleable.tableView_icon_imageHeight, 29.0F);
                iconFont = localTypedArray.getString(R.styleable.tableView_icon_font);
                iconFontColor = localTypedArray.getColor(R.styleable.tableView_icon_font_color, 0);
                leftText = localTypedArray.getString(R.styleable.tableView_left_text);
                leftText_2 = localTypedArray.getString(R.styleable.tableView_left_text_2);
                leftText_3 = localTypedArray.getString(R.styleable.tableView_left_text_3);
                rightText = localTypedArray.getString(R.styleable.tableView_right_text);
                rightImage = localTypedArray.getDrawable(R.styleable.tableView_right_image);
                isShowTogglebutton = localTypedArray.getBoolean(R.styleable.tableView_show_togglebutton, false);//默认不显示选择按钮
            }
        } finally {
            if (localTypedArray != null) {
                localTypedArray.recycle();
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mIvTableArrow = ((ImageView)findViewById(R.id.table_layout_arrow));
        mIvTableIconImage = ((ImageView)findViewById(R.id.table_layout_icon_image));
        mTvTableIconFont = (FontAwesomeText) findViewById(R.id.table_layout_icon_font);
        mTvTableLeftText = ((TextView)findViewById(R.id.table_layout_left_text));
        mTvTableLeftText_2 = ((TextView)findViewById(R.id.table_layout_left_text_2));
        mTvTableLeftText_3 = ((TextView)findViewById(R.id.table_layout_left_text_3));
        mTvTableRightText = ((TextView)findViewById(R.id.table_layout_right_text));
        mIvTableRightImage = ((ImageView)findViewById(R.id.table_layout_right_image));
        mIvTableIndicator = ((ImageView)findViewById(R.id.table_layout_new_indicator));
        mTbTableToggleButton = ((SwitchCompat)findViewById(R.id.table_layout_toggleButton));

        setBackgroundResource(R.drawable.table_view_selector);
        if (isChangeBackgroud)
            setOnClickListener(this);
        if (isShowArrow)
            setTableArrowVisibility(View.VISIBLE);
        if (iconImage != null) {
            showTableIconImage(iconImage);
            if (isIconLargeSize) {
                setTableIconImageWidthAndHeight(iconImageWidth, iconImageHeight);
            }
        }
        if (iconFont != null) {
            showTableIconFont(iconFont);
            if (iconFontColor != 0)
                setTableIconFontColor(iconFontColor);
        }
        if (leftText != null)
            showLeftText(leftText);
        if (leftText_2 != null)
            showLeftText_2(leftText_2);
        if (leftText_3 != null)
            showLeftText_3(leftText_3);
        if (rightText != null)
            showRightText(rightText);
        if (rightImage != null)
            showRightImage(rightImage);
        if (isShowTogglebutton)
            showToggleButton();
    }

    @Override
    public void onClick(View v) {
        if (isShowTogglebutton)
            mTbTableToggleButton.toggle();
    }

    /**
     * 显示TableArrow
     * @param visibility
     */
    private void setTableArrowVisibility(int visibility) {
        mIvTableArrow.setVisibility(visibility);
    }

    /**
     * 显示TableIcon
     * @param resid
     */
    public final void showTableIconImage(int resid) {
        mTvTableIconFont.setVisibility(View.GONE);
        if (mIvTableIconImage.getVisibility() != View.VISIBLE)
            mIvTableIconImage.setVisibility(View.VISIBLE);
        mIvTableIconImage.setBackgroundResource(resid);
    }

    /**
     * 显示TableIcon
     * @param bm
     */
    public final void showTableIconImage(Bitmap bm) {
        mTvTableIconFont.setVisibility(View.GONE);
        if (mIvTableIconImage.getVisibility() != View.VISIBLE)
            mIvTableIconImage.setVisibility(View.VISIBLE);
        mIvTableIconImage.setImageBitmap(bm);
    }

    /**
     * 显示TableIcon
     * @param drawable
     */
    public final void showTableIconImage (Drawable drawable) {
        mTvTableIconFont.setVisibility(View.GONE);
        if (mIvTableIconImage.getVisibility() != View.VISIBLE)
            mIvTableIconImage.setVisibility(View.VISIBLE);
        mIvTableIconImage.setImageDrawable(drawable);
    }

    /**
     * 设置TableIcon大小
     * @param width
     * @param height
     */
    public final void setTableIconImageWidthAndHeight(float width, float height) {
        LayoutParams localLayoutParams = (LayoutParams) mIvTableIconImage.getLayoutParams();
        localLayoutParams.width = (int)width;
        localLayoutParams.height = (int)height;
    }

    /**
     * 显示TableIcon
     * @param str
     */
    private void showTableIconFont(String str) {
        mIvTableIconImage.setVisibility(View.GONE);
        if (mTvTableIconFont.getVisibility() != View.VISIBLE)
            mTvTableIconFont.setVisibility(View.VISIBLE);
        mTvTableIconFont.setText(str);
    }

    /**
     * 显示TableIcon
     * @param resid
     */
    public final void showTableIconFont(int resid) {
        mIvTableIconImage.setVisibility(View.GONE);
        if (mTvTableIconFont.getVisibility() != View.VISIBLE)
            mTvTableIconFont.setVisibility(View.VISIBLE);
        mTvTableIconFont.setText(resid);
    }

    /**
     * 设置TableIcon颜色
     * @param color
     */
    public final void setTableIconFontColor(int color) {
        mTvTableIconFont.setTextColor(color);
    }

    /**
     * 显示LeftText
     * @param str
     */
    public final void showLeftText(String str) {
        if (mTvTableLeftText.getVisibility() != View.VISIBLE)
            mTvTableLeftText.setVisibility(View.VISIBLE);
        mTvTableLeftText.setText(str);
    }

    /**
     * 显示LeftText_2
     * @param str
     */
    public final void showLeftText_2(String str) {
        if (mTvTableLeftText_2.getVisibility() != View.VISIBLE)
            mTvTableLeftText_2.setVisibility(View.VISIBLE);
        mTvTableLeftText_2.setText(str);
    }

    /**
     * 显示LeftText_3
     * @param str
     */
    public final void showLeftText_3(String str) {
        if (mTvTableLeftText_3.getVisibility() != View.VISIBLE)
            mTvTableLeftText_3.setVisibility(View.VISIBLE);
        mTvTableLeftText_3.setText(str);
    }

    /**
     * 显示RightText
     * @param str
     */
    public final void showRightText(String str) {
        mIvTableRightImage.setVisibility(View.GONE);
        mTbTableToggleButton.setVisibility(View.GONE);
        if (mTvTableRightText.getVisibility() != View.VISIBLE)
            mTvTableRightText.setVisibility(View.VISIBLE);
        mTvTableRightText.setText(str);
    }

    /**
     * 显示RightImage
     * @param resId
     */
    public final void showRightImage(int resId) {
        mTvTableRightText.setVisibility(View.INVISIBLE);
        mTbTableToggleButton.setVisibility(View.GONE);
        if (mIvTableRightImage.getVisibility() != View.VISIBLE)
            mIvTableRightImage.setVisibility(View.VISIBLE);
        mIvTableRightImage.setImageResource(resId);
    }

    /**
     * 显示RightImage
     * @param bm
     */
    public final void showRightImage(Bitmap bm) {
        mTvTableRightText.setVisibility(View.INVISIBLE);
        mTbTableToggleButton.setVisibility(View.GONE);
        if (mIvTableRightImage.getVisibility() != View.VISIBLE)
            mIvTableRightImage.setVisibility(View.VISIBLE);
        mIvTableRightImage.setImageBitmap(bm);
    }

    /**
     * 显示RightImage
     * @param drawable
     */
    public final void showRightImage(Drawable drawable) {
        mTvTableRightText.setVisibility(View.INVISIBLE);
        mTbTableToggleButton.setVisibility(View.GONE);
        if (mIvTableRightImage.getVisibility() != View.VISIBLE)
            mIvTableRightImage.setVisibility(View.VISIBLE);
        mIvTableRightImage.setImageDrawable(drawable);
    }

    /**
     * 显示ToggleButton
     */
    private void showToggleButton() {
        mTvTableRightText.setVisibility(View.INVISIBLE);
        mIvTableRightImage.setVisibility(View.GONE);
        setTableArrowVisibility(View.INVISIBLE);
        if (mTbTableToggleButton.getVisibility() != View.VISIBLE)
            mTbTableToggleButton.setVisibility(View.VISIBLE);
    }

    /**
     * 显示小红点
     */
    public final void showIndicator() {
        if (mIvTableIndicator.getVisibility() != View.VISIBLE)
            mIvTableIndicator.setVisibility(View.VISIBLE);
    }

    /**
     * 显示小红点
     */
    public final void hideIndicator() {
        if (mIvTableIndicator.getVisibility() != View.GONE)
            mIvTableIndicator.setVisibility(View.GONE);
    }

    public final ImageView getTableIconImage() {
        return this.mIvTableIconImage;
    }

    public final ImageView getTableRightImage() {
        return this.mIvTableRightImage;
    }

    public final SwitchCompat getTableToggleButton() {
        return this.mTbTableToggleButton;
    }
}



























