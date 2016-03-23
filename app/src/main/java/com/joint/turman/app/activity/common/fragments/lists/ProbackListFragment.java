package com.joint.turman.app.activity.common.fragments.lists;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.fragments.lists.adapters.ProbackAdapter;
import com.joint.turman.app.base.BaseListFragment;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.Proback;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.ProbackListCallback;
import com.joint.turman.app.service.ProbackService;

import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/15.
 */
public class ProbackListFragment extends BaseListFragment<Proback, ProbackAdapter> {
    private ProbackListCallback callback = new ProbackListCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(ListResult<Proback> response) {
            Status status = response.getResult();
            if (status.getErrorCode() == 1) {
                ListEntity<Proback> result = response.getData();
                List<Proback> list = result.getList();
                if (list != null && list.size() > 0) {
                    if (entityList == null) {
                        entityList = (LinkedList<Proback>) list;
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
    protected ProbackAdapter getAdapter() {
        return new ProbackAdapter(entityList, _context);
    }

    @Override
    protected void loadData() {
        super.loadData();
        ProbackService.getList(params, callback);
    }

    @Override
    public int getSearchHint() {
        return R.string.search_hint_proback;
    }

    @Override
    protected void itemSelected(int position) {
    }
}
