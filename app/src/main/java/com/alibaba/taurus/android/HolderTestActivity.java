package com.alibaba.taurus.android;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import io.reactivex.functions.Action;

public class HolderTestActivity extends Activity {

    private static RouteIntent routeIntent;

    public static void setRouteIntent(RouteIntent routeIntent) {
        HolderTestActivity.routeIntent = routeIntent;
    }

    private int requestCode;
    private int resultCode;

    private Intent data;

    private static final int ACTIVITY_REQUEST_CODE=200;

    private static final int ACTIVITY_RESULT_CODE=202;


    public static void startActivityForResultCallback(Context context, Intent intent, ResultCallback callback) {
        RouteIntent routeIntent=new RouteIntent(intent);
        routeIntent.setCallback(callback);
        HolderTestActivity.setRouteIntent(routeIntent);
        context.startActivity(new Intent(context, HolderTestActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (routeIntent == null) {
            finish();
            return;
        }

        if (savedInstanceState != null) return;

        try {
            startActivityForResult(routeIntent.getIntent(), ACTIVITY_REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            if (routeIntent.getCallback() != null) {
                routeIntent.getCallback().onError(e);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.requestCode=resultCode;
        this.resultCode=resultCode;
        this.data=data;

        routeIntent.setRequestCode(requestCode);
        routeIntent.setResultCode(resultCode);
        routeIntent.setIntent(data);
        finish();
    }


    /**
     * 最终输出
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(routeIntent.getCallback()!=null){
            routeIntent.getCallback().onSuccess(requestCode,resultCode,data);
        }
    }
}
