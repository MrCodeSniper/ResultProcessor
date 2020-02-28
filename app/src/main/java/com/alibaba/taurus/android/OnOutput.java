package com.alibaba.taurus.android;

import android.content.Intent;
import android.support.annotation.Nullable;

import java.io.Serializable;

public interface OnOutput extends Serializable {
    /**
     * onActivityResult回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
   void response(int requestCode, int resultCode, @Nullable Intent data);


    /**
     * 回调错误
     * @param throwable
     */
   void error(Throwable throwable);
}
