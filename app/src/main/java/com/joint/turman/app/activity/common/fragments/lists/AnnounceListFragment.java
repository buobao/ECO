package com.joint.turman.app.activity.common.fragments.lists;

import com.joint.turman.app.activity.common.fragments.lists.adapters.AnnounceAdapter;
import com.joint.turman.app.base.BaseListFragment;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.Announce;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.AnnounceListCallback;
import com.joint.turman.app.service.AnnounceService;

import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/16.
 */
public class AnnounceListFragment extends BaseListFragment<Announce, AnnounceAdapter> {

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
    protected void loadData() {
        super.loadData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                AnnounceService.getList(params,callback);
            }
        }).start();
    }

    @Override
    protected AnnounceAdapter getAdapter() {
        return new AnnounceAdapter(entityList, _context);
    }
}
