package com.alibaba.taurus.android;

import android.content.Intent;

public interface ResultCallback {


    void onSuccess(int requestCode, int resultCode, Intent data);


    void onError(Throwable throwable);

}
