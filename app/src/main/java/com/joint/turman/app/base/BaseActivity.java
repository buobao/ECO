package com.joint.turman.app.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joint.turman.app.R;
import com.joint.turman.app.sys.TurmanApplication;
import com.joint.turman.customwidget.dialog.DialogHelper;
import com.joint.turman.customwidget.dialog.WaitDialog;

/**
 * Created by Administrator on 2016/3/4.
 */
public class BaseActivity extends AppCompatActivity {

    private boolean _isVisible;
    private WaitDialog _waitDialog;  //wait dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onPause() {
        _isVisible = false;
        super.onPause();
    }

    @Override
    protected void onResume() {
        _isVisible = true;
        super.onResume();
    }

    public boolean isVisible() {
        return _isVisible;
    }

    protected void init(){
        setContentView(getLayout());
    }

    protected int getLayout(){
        return -1;
    }

    @Override
    public void onBackPressed() {
        TurmanApplication.popActivity();
    }

    public WaitDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    public WaitDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    public WaitDialog showWaitDialog(String message) {
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = DialogHelper.getCancelableWaitDialog(this, message);
            }
            if (_waitDialog != null) {
                _waitDialog.setMessage(message);
                _waitDialog.show();
            }
            return _waitDialog;
        }
        return null;
    }

}
