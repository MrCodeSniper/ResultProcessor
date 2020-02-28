package com.alibaba.taurus.android;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class ResultFragment extends Fragment {

    public final PublishSubject<ActivityResult> resultPublisher = PublishSubject.create();
    public final BehaviorSubject<Boolean> isAttachedBehavior = BehaviorSubject.createDefault(false);

    public PublishSubject<ActivityResult> getResultPublisher() {
        return resultPublisher;
    }

    public BehaviorSubject<Boolean> getIsAttachedBehavior() {
        return isAttachedBehavior;
    }

    public ResultFragment() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isAttachedBehavior.onNext(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        resultPublisher.onNext(new ActivityResult(requestCode,resultCode,data));
    }
}
