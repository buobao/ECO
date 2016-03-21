package com.joint.turman.app.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.joint.turman.app.sys.TurmanApplication;

/**
 * Created by dqf on 2016/3/11.
 */
public class BaseFragment extends Fragment {
    protected TurmanApplication _app;
    protected Context _context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _context = getActivity();
        _app = (TurmanApplication)_context.getApplicationContext();
        initCreate(savedInstanceState);
    }

    public void initCreate(Bundle bundle){}
}
