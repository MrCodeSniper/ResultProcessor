package com.alibaba.taurus.android;

import android.content.Intent;
import android.support.annotation.Nullable;

import io.reactivex.Observable;

public interface OnPreOutput<T> {
    Observable<T> response(int requestCode, int resultCode, @Nullable Intent data);
}
