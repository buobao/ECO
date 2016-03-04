package com.joint.turman.app.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.joint.turman.app.R;
import com.joint.turman.app.ui.drawable.MaterialMenuDrawable;

/**
 * Created by dqf on 2016/3/3.
 */
public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private LayoutInflater mInflater;

    //侧滑菜单
    private DrawerLayout mDrawerLayout;
    private MaterialMenuDrawable materialMenuDrawable;

    private View mLeftMenu;

    protected Toolbar mActionBar;


    private boolean isMenuOpened;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);

        mLeftMenu = findViewById(R.id.act_home_leftMenu);

        //设置顶部工具栏
        mActionBar = (Toolbar) findViewById(R.id.act_home_actionbar);
        mActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawable();
            }
        });
        mInflater = getLayoutInflater();
        //initActionBar(mActionBar);

        //初始化侧滑菜单
        mDrawerLayout = (DrawerLayout) findViewById(R.id.act_home_mainContainer);
        materialMenuDrawable = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        mActionBar.setNavigationIcon(materialMenuDrawable);
        materialMenuDrawable.setNeverDrawTouch(true);
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                materialMenuDrawable.setTransformationOffset(
                        MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                        isMenuOpened ? 2 - slideOffset : slideOffset
                );
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                isMenuOpened = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isMenuOpened = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (newState == DrawerLayout.STATE_IDLE) {
                    if (isMenuOpened)
                        materialMenuDrawable.setIconState(MaterialMenuDrawable.IconState.ARROW);
                    else materialMenuDrawable.setIconState(MaterialMenuDrawable.IconState.BURGER);
                }
            }
        });
    }

    /**
     * 显示隐藏菜单
     */
    private void toggleDrawable(){
        if(isMenuOpened)
            mDrawerLayout.closeDrawer(mLeftMenu);
        else
            mDrawerLayout.openDrawer(mLeftMenu);
    }
}
