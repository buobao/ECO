package com.joint.turman.app.activity.common.fragments.contexts;

import android.view.View;
import android.widget.Button;

import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseContextFragment;
import com.joint.turman.app.entity.User;
import com.joint.turman.customwidget.tableview.TableView;

/**
 * Created by dqf on 2016/3/11.
*/
public class ProfileFragment extends BaseContextFragment<User> implements View.OnClickListener {

    private TableView mCompany,mDepartment,mName,mSex,mPhone;
    private Button mLogout;

    @Override
    protected int getLayoutRes() {
        return R.layout.frg_profile;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        entity = _app.getUserInfo();
        mCompany = (TableView) view.findViewById(R.id.user_info_company);
        mDepartment = (TableView) view.findViewById(R.id.user_info_department);
        mName = (TableView) view.findViewById(R.id.user_info_name);
        mSex = (TableView) view.findViewById(R.id.user_info_sex);
        mPhone = (TableView) view.findViewById(R.id.user_info_mobile);

        //这里的数据应该是通过发送请求获取，这里个人信息比较特殊，在用户登录后就已经获取并缓存，所以直接读取即可
        //这里的人员信息暂时设置为不可编辑
        mCompany.showRightText(entity.getCompanyName());
        mDepartment.showRightText(entity.getDepartmentName());
        mName.showRightText(entity.getName());
        mSex.showRightText("male");
        mPhone.showRightText(entity.getPhone());

        mLogout = (Button) view.findViewById(R.id.btn_logout);
        mLogout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout:
                _app.cancelAccount(_context);
                break;
        }
    }
}
