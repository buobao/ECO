package com.joint.turman.app.activity.common.fragments.contexts;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseContextFragment;
import com.joint.turman.app.bean.Result;
import com.joint.turman.app.entity.Client;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.ClientEntityCallback;
import com.joint.turman.app.service.ClientService;
import com.joint.turman.customwidget.section.ReadableSection;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/22.
 */
public class ClientContextFragment extends BaseContextFragment<Client> {

    private ReadableSection mName,mSubname,mTaitou,mNature,mAddress,mRemark,mOthers;

    private ClientEntityCallback callback = new ClientEntityCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(Result<Client> response) {
            Status status = response.getResult();
            Client entity = null;
            if (status.getErrorCode() == 1){
                    entity = response.getData();
            }
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",entity);
            msg.setData(bundle);
            mHandler.sendMessage(msg);
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.frg_client_context;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mName = (ReadableSection) view.findViewById(R.id.frg_client_name);
        mSubname = (ReadableSection) view.findViewById(R.id.frg_client_subname);
        mTaitou = (ReadableSection) view.findViewById(R.id.frg_client_taitou);
        mNature = (ReadableSection) view.findViewById(R.id.frg_client_nature);
        mAddress = (ReadableSection) view.findViewById(R.id.frg_client_address);
        mRemark = (ReadableSection) view.findViewById(R.id.frg_client_remark);
        mOthers = (ReadableSection) view.findViewById(R.id.frg_client_others);
        ClientService.getDetail(params,callback);
    }

    @Override
    protected void setForm() {
        mName.setText(mEntity.getName());
        mSubname.setText(mEntity.getSubName());
        mTaitou.setText(mEntity.getInvoiceTitle());
        mNature.setText(mEntity.getTypeName());
        mAddress.setText(mEntity.getProvince()+mEntity.getCity()+mEntity.getCounty());
        mRemark.setText(mEntity.getRemark());
        mOthers.setText(mEntity.getSitutation());
    }
}
