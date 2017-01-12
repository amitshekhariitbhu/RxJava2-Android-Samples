package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.AbsExampleActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class TakeExampleActivity extends AbsExampleActivity {

    /*
    * Using take operator, it only emits
    * required number of values. here only 3 out of 5
    */
    protected void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .take(3)
                .subscribe(getObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.just(1, 2, 3, 4, 5);
    }

}