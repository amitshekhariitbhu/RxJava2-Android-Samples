package com.rxjava2.android.samples.ui.operators;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Predicate;

public class TakeWhileExampleActivity extends TakeOperatorBaseActivity {

    private static final String TAG = TakeWhileExampleActivity.class.getSimpleName();

    @Override
    protected void doSomeWork() {
        getStringObservable()
                //Delay item emission by one second
                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (s, aLong) -> s)
                //Take the items until the condition is met.
                .takeWhile(s -> !s.toLowerCase().contains("honey"))
                //We need to observe on MainThread because delay works on background thread to avoid UI blocking.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }
}
