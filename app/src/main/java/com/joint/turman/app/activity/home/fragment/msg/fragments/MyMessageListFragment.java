package com.joint.turman.app.activity.home.fragment.msg.fragments;

import com.joint.turman.app.base.BaseListFragment;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.Comment;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.CommentListCallback;
import com.joint.turman.app.service.CommentService;

import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/21.
 */
public class MyMessageListFragment extends BaseListFragment<Comment, MyMessageAdapter> {

    private CommentListCallback callback = new CommentListCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(ListResult<Comment> response) {
            Status status = response.getResult();
            if (status.getErrorCode() == 1) {
                ListEntity<Comment> result = response.getData();
                List<Comment> list = result.getList();
                if (list != null && list.size() > 0) {
                    if (entityList == null) {
                        entityList = (LinkedList<Comment>) list;
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
    protected MyMessageAdapter getAdapter() {
        return new MyMessageAdapter(entityList, _context);
    }

    @Override
    protected void loadData() {
        super.loadData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                CommentService.getAboutMeList(params,callback);
            }
        }).start();
    }
}















