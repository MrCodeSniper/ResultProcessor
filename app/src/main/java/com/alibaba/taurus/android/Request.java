package com.alibaba.taurus.android;

import android.content.Intent;

/**
 * 每次跳转再到回调的过程
 */
public class Request {

    private Intent intent;
    private OnPreOutput input;
    private OnOutput output;

    public Request(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public OnPreOutput getInput() {
        return input;
    }

    public void setInput(OnPreOutput input) {
        this.input = input;
    }

    public OnOutput getOutput() {
        return output;
    }

    public void setOutput(OnOutput output) {
        this.output = output;
    }
}
