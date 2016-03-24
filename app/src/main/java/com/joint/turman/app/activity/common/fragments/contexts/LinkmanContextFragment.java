package com.joint.turman.app.activity.common.fragments.contexts;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseContextFragment;
import com.joint.turman.app.bean.Result;
import com.joint.turman.app.entity.Linkman;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.LinkmanEntityCallback;
import com.joint.turman.app.service.LinkmanService;
import com.joint.turman.customwidget.section.ReadableSection;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/24.
 */
public class LinkmanContextFragment extends BaseContextFragment<Linkman> {
    private LinkmanEntityCallback callback = new LinkmanEntityCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(Result<Linkman> response) {
            Status status = response.getResult();
            Linkman entity = null;
            if (status.getErrorCode() == 1){
                entity = response.getData();
            }
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable(MESSAGE_DATA,entity);
            msg.setData(bundle);
            mHandler.sendMessage(msg);
        }
    };

    private ReadableSection mName,mInfo,mSubName,mDuty,mTel,
            mMobile,mMobileBack,mEmail,mBirthday,mPrefrence,mRemark;

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mName = (ReadableSection) view.findViewById(R.id.frg_linkman_name);
        mInfo = (ReadableSection) view.findViewById(R.id.frg_linkman_info);
        mSubName = (ReadableSection) view.findViewById(R.id.frg_linkman_subname);
        mDuty = (ReadableSection) view.findViewById(R.id.frg_linkman_duty);
        mTel = (ReadableSection) view.findViewById(R.id.frg_linkman_tel);
        mMobile = (ReadableSection) view.findViewById(R.id.frg_linkman_mobile);
        mMobileBack = (ReadableSection) view.findViewById(R.id.frg_linkman_mobile_back);
        mEmail = (ReadableSection) view.findViewById(R.id.frg_linkman_email);
        mPrefrence = (ReadableSection) view.findViewById(R.id.frg_linkman_preference);
        mRemark = (ReadableSection) view.findViewById(R.id.frg_linkman_remark);
        LinkmanService.getDetail(params, callback);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frg_linkman_context;
    }

    @Override
    protected void setForm() {
        mName.setText(mEntity.getName());
        mInfo.setText(mEntity.getClientName());
        mSubName.setText(mEntity.getSubName());
        mDuty.setText(mEntity.getDuty());
        mTel.setText(mEntity.getTel());
        mMobile.setText(mEntity.getMobile());
        mMobileBack.setText(mEntity.getMobile_back());
        mEmail.setText(mEntity.getEmail());
        mPrefrence.setText(mEntity.getPreference());
        mRemark.setText(mEntity.getPreference());
    }
}
