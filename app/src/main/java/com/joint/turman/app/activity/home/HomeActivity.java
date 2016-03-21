package com.joint.turman.app.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.ContentEnum;
import com.joint.turman.app.activity.home.fragment.knowledge.KnowledgeFragment;
import com.joint.turman.app.activity.home.fragment.msg.MsgFragment;
import com.joint.turman.app.activity.home.fragment.project.ProjectFragment;
import com.joint.turman.app.base.BaseActivity;
import com.joint.turman.app.sys.TurmanApplication;
import com.joint.turman.app.ui.drawable.MaterialMenuDrawable;
import com.joint.turman.customwidget.bottomtab.BottomTab;
import com.joint.turman.customwidget.bottomtab.BottomTabGroup;
import com.joint.turman.customwidget.tableview.TableView;

/**
 * Created by dqf on 2016/3/3.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private static final int MSG_BOTTOM_TAB = 1;
    private static final int PROJECT_BOTTOM_TAB = 2;
    private static final int KNOWLEDGE_BOTTOM_TAB = 3;


    private static final String TAG = "HomeActivity";

    private LayoutInflater mInflater;

    //侧滑菜单
    private DrawerLayout mDrawerLayout;
    private MaterialMenuDrawable materialMenuDrawable;
    private View mLeftMenu;
    //工具栏
    protected Toolbar mActionBar;
    //底部菜单
    private BottomTabGroup mBottomTabGroup;
    BottomTab mTab1,mTab2,mTab3;
    private LinearLayout mContent;

    //菜单是否打开
    private boolean isMenuOpened=false;
    //当前在哪个切换页
    private int currIndex=MSG_BOTTOM_TAB;

    private FragmentManager mFragmentManager;

    @Override
    protected int getLayout() {
        return R.layout.act_home;
    }

    @Override
    protected void init() {
        super.init();
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

        mFragmentManager = getSupportFragmentManager();

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

        //注册菜单事件
        mLeftMenu = findViewById(R.id.act_home_leftMenu);
        ViewGroup viewGroup = (ViewGroup) mLeftMenu.findViewById(R.id.leftmenu_list);
        for (int i=0;i<viewGroup.getChildCount();i++){
            View view = viewGroup.getChildAt(i);
            if (view instanceof TableView){
                view.setOnClickListener(this);
            }
        }

        //初始化底部菜单
        mContent = (LinearLayout) findViewById(R.id.act_home_content);
        mBottomTabGroup = (BottomTabGroup) mContent.findViewById(R.id.tab_group);
        mTab1 = (BottomTab) mBottomTabGroup.getChildAt(0);
        mTab2 = (BottomTab) mBottomTabGroup.getChildAt(1);
        mTab3 = (BottomTab) mBottomTabGroup.getChildAt(2);
        mTab1.setChecked(true);  //设置为默认选中
        //设置底部切换事件
        mBottomTabGroup.setOnCheckedChangeListener(new BottomTabGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(BottomTabGroup root, int checkedId) {

                switch (checkedId) {
                    case R.id.tab_01:
                        exchangeTab(MSG_BOTTOM_TAB);
                        break;
                    case R.id.tab_02:
                        exchangeTab(PROJECT_BOTTOM_TAB);
                        break;
                    case R.id.tab_03:
                        exchangeTab(KNOWLEDGE_BOTTOM_TAB);
                        break;
                }
            }
        });

        createFragment(MSG_BOTTOM_TAB);

        //以下是设置红点的代码
//        setTabHint(mTab1,20);
//        setTabHint(mTab2,43);
//        setTabHint(mTab3,105);
    }

    //创建底部菜单fragment
    private void createFragment(int flag){
        Fragment fragment = null;
        switch (flag) {
            case MSG_BOTTOM_TAB:
                fragment = new MsgFragment();
                break;
            case PROJECT_BOTTOM_TAB:
                fragment = new ProjectFragment();
                break;
            case KNOWLEDGE_BOTTOM_TAB:
                fragment = new KnowledgeFragment();
                break;
        }

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.content_fragment, fragment);
        transaction.commit();
    }

    //设置tab上的数字
    private void setTabHint(BottomTab tab, int number){
        tab.getHintTextView().setBackgroundResource(R.drawable.hint_red);
        if (number < 100) {
            tab.setHint(number + "");
        } else {
            tab.setHint("99+");
        }
        tab.getHintTextView().setTextColor(0xffffffff);
    }

    //清除tab hint
    private void clearHint(BottomTab tab){
        tab.getHintTextView().setText("");
        tab.getHintTextView().setVisibility(View.GONE);
    }

    //tab切换处理
    private void exchangeTab(int index){
        if (index == currIndex){
            return;
        }

        switch (currIndex){
            case MSG_BOTTOM_TAB:
                clearHint(mTab1);
                break;
            case PROJECT_BOTTOM_TAB:
                clearHint(mTab2);
                break;
            case KNOWLEDGE_BOTTOM_TAB:
                clearHint(mTab3);
                break;
        }
        createFragment(index);
        currIndex = index;
    }

    /**
     * 显示隐藏菜单
     */
    private void toggleDrawable(){
        if(isMenuOpened) {
            mDrawerLayout.closeDrawer(mLeftMenu);
        }
        else {
            mDrawerLayout.openDrawer(mLeftMenu);
        }
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = null;
        switch (v.getId()) {
            case R.id.leftmenu_myinfo:
                //Toast.makeText(HomeActivity.this,"个人信息",Toast.LENGTH_SHORT).show();
                bundle = TurmanApplication.getContentBundle(ContentEnum.PROFILE);
                break;
            case R.id.leftmenu_client:
                //Toast.makeText(HomeActivity.this,"客户信息",Toast.LENGTH_SHORT).show();
                bundle = TurmanApplication.getContentBundle(ContentEnum.CLIENT);
                break;
            case R.id.leftmenu_linkman:
                bundle = TurmanApplication.getContentBundle(ContentEnum.LINKMAN);
                break;
            case R.id.leftmenu_project:
                bundle = TurmanApplication.getContentBundle(ContentEnum.PROJECT);
                break;
            case R.id.leftmenu_check:
                bundle = TurmanApplication.getContentBundle(ContentEnum.CHECKINFO);
                break;
            case R.id.leftmenu_feedback:
                bundle = TurmanApplication.getContentBundle(ContentEnum.PROBACK);
                break;
            case R.id.leftmenu_communication:
                bundle = TurmanApplication.getContentBundle(ContentEnum.COMMENT);
                break;
            case R.id.leftmenu_notice:
                bundle = TurmanApplication.getContentBundle(ContentEnum.ANNOUNCE);
                break;
            case R.id.leftmenu_setting:
                bundle = TurmanApplication.getContentBundle(ContentEnum.SETTING);
                break;
        }
        TurmanApplication.openCommonActivity(HomeActivity.this, bundle);
    }

    @Override
    public void onBackPressed() {
        TurmanApplication.checkExit(this);
    }

}
