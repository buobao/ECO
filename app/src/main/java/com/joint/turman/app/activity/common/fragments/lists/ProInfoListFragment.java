package com.joint.turman.app.activity.common.fragments.lists;

import android.os.Bundle;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.ContentEnum;
import com.joint.turman.app.activity.common.fragments.lists.adapters.ProInfoAdapter;
import com.joint.turman.app.base.BaseListFragment;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.ProInfo;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.ProinfoListCallback;
import com.joint.turman.app.service.ProinfoService;
import com.joint.turman.app.sys.TurmanApplication;

import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/17.
 */
public class ProInfoListFragment extends BaseListFragment<ProInfo, ProInfoAdapter> {
    private ProinfoListCallback callback = new ProinfoListCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(ListResult<ProInfo> response) {
            Status status = response.getResult();
            if (status.getErrorCode() == 1) {
                ListEntity<ProInfo> result = response.getData();
                List<ProInfo> list = result.getList();
                if (list != null && list.size() > 0) {
                    if (entityList == null) {
                        entityList = (LinkedList<ProInfo>) list;
                        mhandler.sendEmptyMessage(INIT_LOADING);
                    } else {
                        entityList.addAll(list);
                        mhandler.sendEmptyMessage(PAGE_LOADING);
                    }
                } else {
                    mhandler.sendEmptyMessage(NO_LOADING);
                }
            }
        }
    };

    @Override
    protected ProInfoAdapter getAdapter() {
        return new ProInfoAdapter(entityList, _context);
    }

    @Override
    protected void loadData() {
        super.loadData();
        ProinfoService.getList(params, callback);
    }

    @Override
    public int getSearchHint() {
        return R.string.search_hint_proinfo;
    }

    @Override
    protected void itemSelected(int position) {
        String id = entityList.get(position).getId();
        Bundle bundle = TurmanApplication.getContentBundle(ContentEnum.PROJECT_DETAIL,id);
        TurmanApplication.openCommonActivity(getContext(), bundle);
    }
}
