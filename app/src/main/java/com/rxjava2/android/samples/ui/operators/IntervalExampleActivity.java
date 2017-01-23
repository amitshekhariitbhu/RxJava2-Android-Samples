package com.rxjava2.android.samples.ui.operators;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class IntervalExampleActivity extends ExampleBaseActivity {

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // clearing it : do not emit after destroy
    }

    /*
     * simple example using interval to run task at an interval of 2 sec
     * which start immediately
     */
    protected void doSomeWork() {
        disposables.add(getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(this.getDisposableObserver()));
    }

    private Observable<? extends Long> getObservable() {
        //第一个参数是initialDelay初始化延迟，第二个参数是间隔时间。
        return Observable.interval(0, 1, TimeUnit.SECONDS);
    }

}