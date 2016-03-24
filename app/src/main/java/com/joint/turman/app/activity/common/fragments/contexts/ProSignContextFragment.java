package com.joint.turman.app.activity.common.fragments.contexts;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.ContentEnum;
import com.joint.turman.app.base.BaseContextFragment;
import com.joint.turman.app.bean.Result;
import com.joint.turman.app.entity.ProSign;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.ProSignEntityCallback;
import com.joint.turman.app.service.ProSignService;
import com.joint.turman.app.sys.TurmanApplication;
import com.joint.turman.customwidget.section.ReadableSection;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/24.
 */
public class ProSignContextFragment extends BaseContextFragment<ProSign> {
    private ReadableSection mProject,mAddress,mLocation,mTime, mMan,mCheckAddress,mStatus;

    private ProSignEntityCallback callback = new ProSignEntityCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(Result<ProSign> response) {
            Status status = response.getResult();
            ProSign entity = null;
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

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mProject = (ReadableSection) view.findViewById(R.id.frg_prosign_project);
        mAddress = (ReadableSection) view.findViewById(R.id.frg_prosign_address);
        mLocation = (ReadableSection) view.findViewById(R.id.frg_prosign_location);
        mTime = (ReadableSection) view.findViewById(R.id.frg_prosign_time);
        mMan = (ReadableSection) view.findViewById(R.id.frg_prosign_man);
        mCheckAddress = (ReadableSection) view.findViewById(R.id.frg_prosign_checkaddress);
        mStatus = (ReadableSection) view.findViewById(R.id.frg_prosign_status);
        ProSignService.getDetail(params,callback);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frg_prosign_context;
    }

    @Override
    protected void setForm() {
        mProject.setText(mEntity.getProInfoName());
        mProject.setOnTextListener(new ReadableSection.OnTextClickListener() {
            @Override
            public void onClick() {
                String id = mEntity.getProInfoId();
                Bundle bundle = TurmanApplication.getContentBundle(ContentEnum.PROJECT_DETAIL, id);
                TurmanApplication.openCommonActivity(getActivity(),bundle);
            }
        });
        mAddress.setText(mEntity.getProInfoArea());
        mLocation.setText(mEntity.getProInfoAddress());
        mTime.setText(mEntity.getCreateDate());
        mMan.setText(mEntity.getCreaterName());
        mCheckAddress.setText(mEntity.getAddress());
        mStatus.setText(mEntity.getStatus());
    }
}
