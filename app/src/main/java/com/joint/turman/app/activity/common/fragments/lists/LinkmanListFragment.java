package com.joint.turman.app.activity.common.fragments.lists;

import android.os.Bundle;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.ContentEnum;
import com.joint.turman.app.activity.common.fragments.lists.adapters.LinkmanAdapter;
import com.joint.turman.app.base.BaseListFragment;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.Linkman;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.LinkmanListCallback;
import com.joint.turman.app.service.LinkmanService;
import com.joint.turman.app.sys.TurmanApplication;

import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/16.
 */
public class LinkmanListFragment extends BaseListFragment<Linkman, LinkmanAdapter> {

    private LinkmanListCallback callback = new LinkmanListCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(ListResult<Linkman> response) {
            Status status = response.getResult();
            if (status.getErrorCode() == 1) {
                ListEntity<Linkman> result = response.getData();
                List<Linkman> list = result.getList();
                if (list != null && list.size() > 0) {
                    if (entityList == null) {
                        entityList = (LinkedList<Linkman>) list;
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
        LinkmanService.getList(params, callback);
    }

    @Override
    protected LinkmanAdapter getAdapter() {
        return new LinkmanAdapter(entityList, _context);
    }

    @Override
    public int getSearchHint() {
        return R.string.search_hint_linkman;
    }

    @Override
    protected void itemSelected(int position) {
        String entity_id = entityList.get(position).getId();
        Bundle bundle  = TurmanApplication.getContentBundle(ContentEnum.LINKMAN_DETAIL, entity_id);
        TurmanApplication.openCommonActivity(getActivity(), bundle);
    }
}
