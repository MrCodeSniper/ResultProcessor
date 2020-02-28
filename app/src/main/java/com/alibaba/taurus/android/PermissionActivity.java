package com.alibaba.taurus.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public class PermissionActivity extends Activity {

    public static final String KEY_PERMISSIONS = "permissions";

    private static final int PERMISSION_REQUEST_CODE=100;

    private static final int PERMISSION_RESULT_CODE=101;

    private static ResultCallback resultCallback;

    public static void request(Context context, String[] permissions, ResultCallback callback) {
        resultCallback = callback;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(KEY_PERMISSIONS, permissions);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (!intent.hasExtra(KEY_PERMISSIONS)) {
            return;
        }
        String[] permissions = getIntent().getStringArrayExtra(KEY_PERMISSIONS);
        if (Build.VERSION.SDK_INT >= 23) {
            PermissionUtils.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE, new IPermissionResult() {
                @Override
                public void onGranted() {
                    if(resultCallback!=null){
                        resultCallback.onSuccess(PERMISSION_REQUEST_CODE,PERMISSION_RESULT_CODE,null);
                        finish();
                    }
                }

                @Override
                public void onDenied(int requestCode, List<String> denyList) {
                    if(resultCallback!=null){
                        resultCallback.onError(new Throwable("zzz"));
                        finish();
                    }
                }

                @Override
                public void cancelPermissions(int requestCode) {
                    if(resultCallback!=null){
                        resultCallback.onError(new Throwable("yyy"));
                        finish();
                    }
                }
            });
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            if(resultCallback!=null){
                resultCallback.onSuccess(PERMISSION_REQUEST_CODE,PERMISSION_RESULT_CODE,null);
                finish();
            }
        }else if(grantResults[0]==PackageManager.PERMISSION_DENIED){
            if(resultCallback!=null){
                resultCallback.onError(new Throwable("xxx"));
                finish();
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}
