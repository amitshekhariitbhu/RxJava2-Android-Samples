package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.ExampleBaseActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class SimpleExampleActivity extends ExampleBaseActivity {

    /*
     * simple example to emit two value one by one
     */
    protected void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("Cricket", "Football");
    }


}