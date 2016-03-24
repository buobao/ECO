package com.joint.turman.app.activity.common.fragments.contexts;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.ContentEnum;
import com.joint.turman.app.base.BaseContextFragment;
import com.joint.turman.app.bean.Result;
import com.joint.turman.app.entity.ProInfo;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.ProinfoEntityCallback;
import com.joint.turman.app.service.ProinfoService;
import com.joint.turman.app.sys.TurmanApplication;
import com.joint.turman.customwidget.section.ReadableSection;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/24.
 */
public class ProinfoContextFragment extends BaseContextFragment<ProInfo> {

    private ReadableSection mName,mType,mClient,mLinkman,mArea,mLocation,mChief,mGroup,
                            mStart,mEnd,mCreater;

    private ProinfoEntityCallback callback = new ProinfoEntityCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(Result<ProInfo> response) {
            Status status = response.getResult();
            ProInfo entity = null;
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
        mName = (ReadableSection) view.findViewById(R.id.frg_proinfo_name);
        mType = (ReadableSection) view.findViewById(R.id.frg_proinfo_type);
        mLinkman = (ReadableSection) view.findViewById(R.id.frg_proinfo_linkman);
        mClient = (ReadableSection) view.findViewById(R.id.frg_proinfo_client);
        mArea = (ReadableSection) view.findViewById(R.id.frg_proinfo_area);
        mLocation = (ReadableSection) view.findViewById(R.id.frg_proinfo_address);
        mChief = (ReadableSection) view.findViewById(R.id.frg_proinfo_chief);
        mGroup = (ReadableSection) view.findViewById(R.id.frg_proinfo_group);
        mStart = (ReadableSection) view.findViewById(R.id.frg_proinfo_start);
        mEnd = (ReadableSection) view.findViewById(R.id.frg_proinfo_end);
        mCreater = (ReadableSection) view.findViewById(R.id.frg_proinfo_creater);
        ProinfoService.getDetail(params,callback);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frg_proinfo_context;
    }

    @Override
    protected void setForm() {
        mName.setText(mEntity.getName());
        mType.setText(mEntity.getCategoryName());
        mClient.setText(mEntity.getClientName());
        mClient.setOnTextListener(new ReadableSection.OnTextClickListener() {
            @Override
            public void onClick() {
                String id = mEntity.getClientId();
                Bundle bundle = TurmanApplication.getContentBundle(ContentEnum.CLIENT_DETAIL, id);
                TurmanApplication.openCommonActivity(getActivity(), bundle);
            }
        });
        mLinkman.setText(mEntity.getLinkmanName());
        mLinkman.setOnTextListener(new ReadableSection.OnTextClickListener() {
            @Override
            public void onClick() {
                String id = mEntity.getLinkmanId();
                Bundle bundle = TurmanApplication.getContentBundle(ContentEnum.LINKMAN_DETAIL, id);
                TurmanApplication.openCommonActivity(getActivity(), bundle);
            }
        });
        mArea.setText(mEntity.getProvince()+mEntity.getCity()+mEntity.getCounty());
        mLocation.setText(mEntity.getAddress());
        mChief.setText(mEntity.getChief());
        mGroup.setText(mEntity.getGroup());
        mStart.setText(mEntity.getStart());
        mEnd.setText(mEntity.getEnd());
        mCreater.setText(mEntity.getCreaterName());
    }
}
