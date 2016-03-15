package com.joint.turman.app.activity.common.fragments.lists;

import android.view.View;

import com.joint.turman.app.activity.common.fragments.lists.adapters.ClientAdapter;
import com.joint.turman.app.base.BaseListFragment;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.Client;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.ClientListCallback;
import com.joint.turman.app.service.ClientService;
import com.joint.turman.app.sys.TurmanApplication;
import com.joint.turman.app.ui.search.SearchBar;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/11.
 */
public class ClientListFragment extends BaseListFragment<Client,ClientAdapter> {

    private SearchBar mSearchBar;

    private ClientListCallback callback = new ClientListCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(ListResult<Client> response) {
            Status status = response.getResult();
            if (status.getErrorCode() == 1) {
                ListEntity<Client> result = response.getData();
                if (entityList == null) {
                    entityList = result.getList();
                    mhandler.sendEmptyMessage(0x01);
                } else {
                    entityList.addAll(result.getList());
                    mhandler.sendEmptyMessage(0x02);
                }
            }
        }
    };

    @Override
    protected ClientAdapter getAdapter() {
        return new ClientAdapter(entityList, _context);
    }

    @Override
    protected void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("pageNum",pageNum);
                map.put("pageSize", TurmanApplication.getPageSize());
                //map.put("catalog",1);
                ClientService.getList(map,callback);
            }
        }).start();
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        loadData();
        showLoading();
    }


}

























