package com.alibaba.taurus.android;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ActivityResultHelper {

    static ActivitiesLifecycleCallbacks activitiesLifecycle;

    private ActivityResultHelper() {
    }

    public static void register(final Application application) {
        activitiesLifecycle = new ActivitiesLifecycleCallbacks(application);
    }

    public static class Builder<T> {

        public Observable<Output<T>> startIntent(final Intent intent) {
            return startIntent(intent, null);
        }

        public Observable<Output<T>> startIntent(final Intent intent, @Nullable OnPreOutput preOutput) {
            return startHolderActivity(new Request(intent), preOutput);
        }

        private Observable<Output<T>> startHolderActivity(Request request, @Nullable OnPreOutput onPreResult) {

            OnOutput onResult = uiTargetActivity ? onResultActivity() : onResultFragment();
            request.setOnResult(onResult);
            request.setOnPreResult(onPreResult);

            HolderActivity.setRequest(request);

            activitiesLifecycle.getOLiveActivity().subscribe(new Consumer<Activity>() {
                @Override
                public void accept(Activity activity) throws Exception {
                    activity.startActivity(new Intent(activity, HolderActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                }
            });

            return subject;
        }
    }
}
