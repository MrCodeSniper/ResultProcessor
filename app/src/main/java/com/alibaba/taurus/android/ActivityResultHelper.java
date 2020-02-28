package com.alibaba.taurus.android;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class ActivityResultHelper {
    private static final String FRAGMENT_TAG = "_RESULT_HANDLE_FRAGMENT_";


    public static Observable<ActivityResult> startActivityForResult(FragmentManager fm, final Intent intent, final int requestCode){
        ResultFragment fragment = (ResultFragment) fm.findFragmentByTag(FRAGMENT_TAG);

        if(fragment==null){
            fragment=new ResultFragment();
            final FragmentTransaction transaction=fm.beginTransaction();
            transaction.add(fragment,FRAGMENT_TAG);
            transaction.commit();
        }else if(fragment.isDetached()){
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.attach(fragment);
            transaction.commit();
        }


        final ResultFragment finalFragment = fragment;
        return fragment.getIsAttachedBehavior()
                .filter(new Predicate<Boolean>() {
                    @Override
                    public boolean test(Boolean isAttach) throws Exception {
                        return isAttach;
                    }
                }).flatMap(new Function<Boolean, ObservableSource<ActivityResult>>() {
                    @Override
                    public ObservableSource<ActivityResult> apply(Boolean aBoolean) throws Exception {
                        finalFragment.startActivityForResult(intent,requestCode);
                        return finalFragment.getResultPublisher();
                    }
                }).filter(new Predicate<ActivityResult>() {
                    @Override
                    public boolean test(ActivityResult activityResult) throws Exception {
                        return activityResult.getRequestCode()==requestCode;
                    }
                });
    }

}

