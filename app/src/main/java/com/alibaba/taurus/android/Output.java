package com.alibaba.taurus.android;

import android.content.Intent;

public class Output<T> {

    private final T view;
    private final int resultCode;
    private final int requestCode;
    private final Intent data;

    public Output(T view, int resultCode, int requestCode, Intent data) {
        this.view = view;
        this.resultCode = resultCode;
        this.requestCode = requestCode;
        this.data = data;
    }

    public T getView() {
        return view;
    }

    public int getResultCode() {
        return resultCode;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public Intent getData() {
        return data;
    }
}
