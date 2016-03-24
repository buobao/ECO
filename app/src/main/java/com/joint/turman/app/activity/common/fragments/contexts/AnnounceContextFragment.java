package com.joint.turman.app.activity.common.fragments.contexts;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseContextFragment;
import com.joint.turman.app.bean.Result;
import com.joint.turman.app.entity.Announce;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.AnnounceEntityCallback;
import com.joint.turman.app.service.AnnounceService;
import com.joint.turman.customwidget.section.ReadableSection;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/24.
 */
public class AnnounceContextFragment extends BaseContextFragment<Announce> {

    private ReadableSection mSubject, mContent, mTime, mObject;

    private AnnounceEntityCallback callback = new AnnounceEntityCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(Result<Announce> response) {
            Status status = response.getResult();
            Announce entity = null;
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
        mSubject = (ReadableSection) view.findViewById(R.id.frg_announce_subject);
        mContent = (ReadableSection) view.findViewById(R.id.frg_announce_content);
        mTime = (ReadableSection) view.findViewById(R.id.frg_announce_time);
        mObject = (ReadableSection) view.findViewById(R.id.frg_announce_object);

        AnnounceService.getDetail(params, callback);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frg_announce_context;
    }

    @Override
    protected void setForm() {
        mSubject.setText(mEntity.getName());
        mContent.setText(mEntity.getContent());
        mTime.setText(mEntity.getPublishTime());
        mObject.setText(mEntity.getUsersSet());
    }
}
