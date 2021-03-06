package com.nasduck.rafikipermissions.demo.permissions;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.nasduck.rafikipermissions.RafikiPermissions;
import com.nasduck.rafikipermissions.demo.R;
import com.nasduck.rafikipermissions.demo.base.BaseActivity;
import com.nasduck.rafikipermissions.demo.utils.ToastUtils;
import com.nasduck.rafikipermissions.result.code.RafikiResultCode;
import com.nasduck.rafikipermissions.result.listener.OnPermissionResultListener;
import com.nasduck.rafikipermissions.result.strategy.impl.PermissionResultCustomStrategy;

public class StorageActivity extends BaseActivity implements
        OnPermissionResultListener {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        findViewById(R.id.tv_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAppSettingClick();
            }
        });
    }

    public void onReadExternalStorageClick(View view) {
        if (RafikiPermissions.getInstance(this)
                .setResultStrategy(new PermissionResultCustomStrategy(this))
                .requestReadExternalStorage()) {
            Toast.makeText(this, "Already granted read external storage permission", Toast.LENGTH_SHORT).show();
        }
    }

    public void onWriteExternalStorageClick(View view) {
        if (RafikiPermissions.getInstance(this)
                .setResultStrategy(new PermissionResultCustomStrategy(this))
                .requestWriteExternalStorage()) {
            Toast.makeText(this, "Already granted write external storage permission", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPermissionsResultGrant(int requestCode) {
        switch (requestCode) {
            case RafikiResultCode.RESULT_CODE_READ_EXTERNAL_STORAGE:
                ToastUtils.showToast(this, "Read External Storage Granted");
                break;
            case RafikiResultCode.RESULT_CODE_WRITE_EXTERNAL_STORAGE:
                ToastUtils.showToast(this, "Write External Storage Granted");
                break;
        }
    }

    @Override
    public void onPermissionsResultDenied(int requestCode) {
        switch (requestCode) {
            case RafikiResultCode.RESULT_CODE_READ_EXTERNAL_STORAGE:
                ToastUtils.showToast(this, "Read External Storage Denied");
                break;
            case RafikiResultCode.RESULT_CODE_WRITE_EXTERNAL_STORAGE:
                ToastUtils.showToast(this, "Write External Storage Denied");
                break;
        }
    }
}
