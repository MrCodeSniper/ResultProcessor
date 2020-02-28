package com.alibaba.taurus.android;

import android.content.Intent;

public class RouteIntent {

    private Intent intent;

    private int requestCode;

    private int resultCode;

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    private ResultCallback callback;

    public RouteIntent(Intent intent) {
        this.intent = intent;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public void setCallback(ResultCallback callback) {
        this.callback = callback;
    }

    public Intent getIntent() {
        return intent;
    }


    public ResultCallback getCallback() {
        return callback;
    }
}
