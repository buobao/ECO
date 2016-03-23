package com.joint.turman.app.activity.common.fragments.contexts;

import android.view.View;

import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseContextFragment;
import com.joint.turman.app.entity.Client;
import com.joint.turman.customwidget.section.ReadableSection;

/**
 * Created by dqf on 2016/3/22.
 */
public class ClientContextFragment extends BaseContextFragment<Client> {
    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frg_client_context;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        ReadableSection name = (ReadableSection) view.findViewById(R.id.frg_client_name);
        name.setText("中国客户");
    }
}
