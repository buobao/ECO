package com.joint.turman.app.activity.home.fragment.knowledge;

import com.joint.turman.app.R;
import com.joint.turman.app.base.BaseListFragment;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.Knowledge;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.KnowledgeListCallback;
import com.joint.turman.app.service.KnowledgeService;

import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/21.
 */
public class KnowledgeFragment extends BaseListFragment<Knowledge, KnowledgeAdapter> {
    private KnowledgeListCallback callback = new KnowledgeListCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(ListResult<Knowledge> response) {
            Status status = response.getResult();
            if (status.getErrorCode() == 1) {
                ListEntity<Knowledge> result = response.getData();
                List<Knowledge> list = result.getList();
                if (list != null && list.size() > 0) {
                    if (entityList == null) {
                        entityList = (LinkedList<Knowledge>) list;
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
    protected KnowledgeAdapter getAdapter() {
        return new KnowledgeAdapter(entityList, _context);
    }

    @Override
    protected void loadData() {
        super.loadData();
        KnowledgeService.getList(params, callback);
    }

    @Override
    public int getSearchHint() {
        return R.string.search_hint_knowledge;
    }

    @Override
    protected void itemSelected(int position) {
    }
}
