package com.joint.turman.app.activity.common.fragments.lists;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.joint.turman.app.R;
import com.joint.turman.app.activity.common.fragments.lists.adapters.ClientAdapter;
import com.joint.turman.app.base.BaseListFragment;
import com.joint.turman.app.bean.ListResult;
import com.joint.turman.app.entity.Client;
import com.joint.turman.app.entity.ListEntity;
import com.joint.turman.app.entity.Status;
import com.joint.turman.app.entity.callback.ClientListCallback;
import com.joint.turman.app.service.ClientService;
import com.joint.turman.app.ui.search.SearchBar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by dqf on 2016/3/11.
 */
public class ClientListFragment extends BaseListFragment<Client,ClientAdapter> {

    private SearchBar mSearchBar;
    private ListView mListView;

    private ClientListCallback callback = new ClientListCallback(){
        @Override
        public void onError(Call call, Exception e) {
            super.onError(call, e);
        }

        @Override
        public void onResponse(ListResult<Client> response) {
            Status status = response.getResult();
            LinkedList<Client> list = null;
            if (status.getErrorCode() == 1) {
                ListEntity<Client> result = response.getData();
                list = result.getList();
            }
            mhandler.sendMessage(mhandler.obtainMessage(0x01, list));
        }
    };

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x01:
                    adapter = new ClientAdapter((LinkedList<Client>) msg.obj, _context);
                    mListView.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mListView = (ListView) view.findViewById(R.id.frg_list);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("pageNum",1);
                map.put("pageSize",2);
                map.put("catalog",1);
                ClientService.getList(map,callback);
            }
        }).start();
    }


}

























