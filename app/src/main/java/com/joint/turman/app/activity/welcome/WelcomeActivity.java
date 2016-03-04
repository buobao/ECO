package com.joint.turman.app.activity.welcome;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joint.turman.app.R;
import com.joint.turman.app.sys.TurmanApplication;

/**
 * Created by dqf on 2016/3/1.
 */
public class WelcomeActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private LinearLayout mShowDot;
    private TextView mStart;

    private DefPagerAdapter mDefPagerAdapter;

    private ImageView[] mImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_welcome);

        mViewPager = (ViewPager) findViewById(R.id.act_welcome_viewPager);
        mShowDot = (LinearLayout) findViewById(R.id.act_welcome_showDot);

        LayoutInflater inflater =getLayoutInflater();
        View view1 = inflater.inflate(R.layout.act_welcome_item1, null,false);
        View view2 = inflater.inflate(R.layout.act_welcome_item2, null,false);
        View view3 = inflater.inflate(R.layout.act_welcome_item3, null,false);
        //获取第三个页面上的开始按钮
        mStart = (TextView) view3.findViewById(R.id.act_welcome_start);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(WelcomeActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                //这里处理登陆信息，如果有登陆记录信息则直接读取用户信息，并发送登陆请求，成果：跳转到主页面，失败：跳转到登陆页面
                //如果没有登录信息，则直接跳转到登陆页
                //以上是自动登录的判断，这里先不考虑自动登录的情况
                TurmanApplication.gotoLogin(WelcomeActivity.this);
            }
        });


        mDefPagerAdapter = new DefPagerAdapter(view1,view2,view3);
        mViewPager.setAdapter(mDefPagerAdapter);
        //设置切换动画
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());

        //这里初始化页面下方切换点
        mImgs = new ImageView[mDefPagerAdapter.getCount()];  //这里切换点的数量等于图片的数量
        for (int i = 0; i < mImgs.length; i++) {
            mImgs[i] = new ImageView(WelcomeActivity.this);
            if (0 == i) {
                mImgs[i].setBackgroundResource(R.drawable.dot_selected);
            } else {
                mImgs[i].setBackgroundResource(R.drawable.dot_unselected);
            }

            //这里为了设置dot之间的距离
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,12,0);   //设置间距为12
            mImgs[i].setLayoutParams(lp);

            mShowDot.addView(mImgs[i]);
        }
        //页面切换事件监听
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int len = mDefPagerAdapter.getCount();
        //更新图标
        for (int i = 0; i < len; i++) {
            if (position == i) {
                mImgs[i].setBackgroundResource(R.drawable.dot_selected);
            } else {
                mImgs[i].setBackgroundResource(R.drawable.dot_unselected);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
