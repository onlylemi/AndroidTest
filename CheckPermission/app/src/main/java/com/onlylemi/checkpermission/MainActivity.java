package com.onlylemi.checkpermission;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected String[] getNeedPermissions() {
        return new String[]{"android.permission.INTERNET", "android.permission.ACCESS_WIFI_STATE",
        "android.permission.ACCESS_COARSE_LOCATION", "android.permission.WRITE_EXTERNAL_STORAGE",
        "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA"};
    }

    @Override
    protected void permissionGrantedSuccess() {
        Toast.makeText(MainActivity.this, "permissionGrantedSuccess", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void permissionGrantedFail() {
        Toast.makeText(MainActivity.this, "permissionGrantedFail", Toast.LENGTH_SHORT).show();
    }
}
