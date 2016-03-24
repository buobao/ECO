package com.joint.turman.app.activity.home.fragment.msg.fragments;

import android.os.Bundle;

import com.joint.turman.app.activity.common.ContentEnum;
import com.joint.turman.app.activity.common.fragments.lists.adapters.AnnounceAdapter;
import com.joint.turman.app.base.BaseListFragment;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.Announce;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.AnnounceListCallback;
import com.joint.turman.app.service.AnnounceService;
import com.joint.turman.app.sys.TurmanApplication;

import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/21.
 */
public class MyAnnounceListFragment extends BaseListFragment<Announce,AnnounceAdapter> {
    private AnnounceListCallback callback = new AnnounceListCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(ListResult<Announce> response) {
            Status status = response.getResult();
            if (status.getErrorCode() == 1) {
                ListEntity<Announce> result = response.getData();
                List<Announce> list = result.getList();
                if (list != null && list.size() > 0) {
                    if (entityList == null) {
                        entityList = (LinkedList<Announce>) list;
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
    protected AnnounceAdapter getAdapter() {
        return new AnnounceAdapter(entityList, _context);
    }

    @Override
    protected void loadData() {
        super.loadData();
        AnnounceService.getRelatedList(params, callback);
    }

    @Override
    public boolean showSearchBar(){
        return false;
    }

    @Override
    protected void itemSelected(int position) {
        String id = entityList.get(position).getId();
        Bundle bundle = TurmanApplication.getContentBundle(ContentEnum.ANNOUNCE_DETAIL, id);
        TurmanApplication.openCommonActivity(getActivity(), bundle);
    }
}
