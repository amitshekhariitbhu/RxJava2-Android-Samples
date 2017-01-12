package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.AbsExampleActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class TimerExampleActivity extends AbsExampleActivity {

    /*
     * simple example using timer to do something after 2 second
     */
    protected void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<? extends Long> getObservable() {
        //延迟2秒后发射一个0.
        // timer就是定时器，到点后仅发射一个0
        return Observable.timer(2, TimeUnit.SECONDS);
    }
}