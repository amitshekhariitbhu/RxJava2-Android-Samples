package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.AbsExampleActivity;

import java.util.Random;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class CompletableObserverExampleActivity extends AbsExampleActivity {

    /*
     * simple example using CompletableObserver
     */
    protected void doSomeWork() {
        //延迟多久发射onComplete
//        Completable completable = Completable.timer(1000, TimeUnit.MILLISECONDS);

        Completable completable = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                if (!e.isDisposed()) {
                    int randomInt = new Random().nextInt(10);
                    if (randomInt % 2 == 0) {
                        e.onComplete();
                    } else {
                        e.onError(new IllegalStateException("Can't completable because of error occur."));
                    }
                }
            }
        });

        completable
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCompletableObserver());
    }


}