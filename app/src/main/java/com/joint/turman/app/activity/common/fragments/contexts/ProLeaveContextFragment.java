package com.joint.turman.app.activity.common.fragments.contexts;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.ContentEnum;
import com.joint.turman.app.base.BaseContextFragment;
import com.joint.turman.app.bean.Result;
import com.joint.turman.app.entity.ProLeave;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.ProLeaveEntityCallback;
import com.joint.turman.app.service.ProLeaveService;
import com.joint.turman.app.sys.TurmanApplication;
import com.joint.turman.customwidget.section.ReadableSection;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/24.
 */
public class ProLeaveContextFragment extends BaseContextFragment<ProLeave> {
    private ReadableSection mProject,mDate,mReason,mMan,mCreateDate;

    private ProLeaveEntityCallback callback = new ProLeaveEntityCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(Result<ProLeave> response) {
            Status status = response.getResult();
            ProLeave entity = null;
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
        mProject = (ReadableSection) view.findViewById(R.id.frg_proleave_project);
        mDate = (ReadableSection) view.findViewById(R.id.frg_proleave_date);
        mReason = (ReadableSection) view.findViewById(R.id.frg_proleave_reason);
        mMan = (ReadableSection) view.findViewById(R.id.frg_proleave_man);
        mCreateDate = (ReadableSection) view.findViewById(R.id.frg_proleave_createDate);
        ProLeaveService.getDetail(params,callback);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frg_proleave_context;
    }

    @Override
    protected void setForm() {
        mProject.setText(mEntity.getProInfoName());
        mProject.setOnTextListener(new ReadableSection.OnTextClickListener() {
            @Override
            public void onClick() {
                String id = mEntity.getProInfoId();
                Bundle bundle = TurmanApplication.getContentBundle(ContentEnum.PROJECT_DETAIL, id);
                TurmanApplication.openCommonActivity(getActivity(), bundle);
            }
        });
        mDate.setText(mEntity.getLeaveDate());
        mReason.setText(mEntity.getReason());
        mMan.setText(mEntity.getCreaterName());
        mCreateDate.setText(mEntity.getCreaterDate());
    }
}
