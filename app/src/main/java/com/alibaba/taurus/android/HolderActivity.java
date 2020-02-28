package com.alibaba.taurus.android;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import io.reactivex.functions.Action;

public class HolderActivity extends Activity {
    private static Request request;

    public static void setRequest(Request request) {
        HolderActivity.request = request;
    }

    private OnPreOutput onPreOutput;
    private OnOutput onOutput;

    private int requestCode;
    private int resultCode;

    private Intent data;

    private static int FAIL_REQUEST_CODE=-888;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (request == null) {
            finish();
            return;
        }

        onPreOutput =request.getInput();
        onOutput=request.getOutput();

        if (savedInstanceState != null) return;

        try {
            startActivityForResult(request.getIntent(), 0);
        } catch (ActivityNotFoundException e) {
            if (onOutput != null) {
                onOutput.error(e);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.resultCode = resultCode;
        this.requestCode = requestCode;
        this.data = data;

        if(this.onPreOutput!=null){
            /**
             * 上游发送数据
             */
            this.onPreOutput.response(requestCode,resultCode,data)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        finish();
                    }
                }).subscribe();
        }else {
            finish();
        }
    }


    /**
     * 最终输出
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(onOutput!=null){
            onOutput.response(requestCode,resultCode,data);
        }
    }
}
