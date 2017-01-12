package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.AbsExampleActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 22/12/16.
 */

public class ThrottleLastExampleActivity extends AbsExampleActivity {

    /*
    * Using throttleLast() -> emit the most recent items emitted by an Observable within
    * periodic time intervals, so here it will emit 2, 6 and 7 as we have simulated it to be the
    * last the element in the interval of 500 millis
    */
    protected void doSomeWork() {
        getObservable()
                .throttleLast(500, TimeUnit.MILLISECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time wait
                Thread.sleep(0);
                emitter.onNext(1); // skip
                emitter.onNext(2); // deliver
                Thread.sleep(505);
                emitter.onNext(3); // skip
                Thread.sleep(99);
                emitter.onNext(4); // skip
                Thread.sleep(100);
                emitter.onNext(5); // skip
                emitter.onNext(6); // deliver
                Thread.sleep(305);
                emitter.onNext(7); // deliver
                Thread.sleep(510);
                emitter.onComplete();
                /*
                0----------500-------------1000----------1500
                0------------505--604--704--1009----------1519
                1,2-----------3----4---5,6----7-----------Complete
                 */
            }
        });
    }

}