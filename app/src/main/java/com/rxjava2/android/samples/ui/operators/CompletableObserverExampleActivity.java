package com.rxjava2.android.samples.ui.operators;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;

import java.util.Random;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class CompletableObserverExampleActivity extends ExampleBaseActivity {

    /*
     * simple example using CompletableObserver
     * Completable does not consist of onNext()
     */
    protected void doSomeWork() {

        Completable completable = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                if (!e.isDisposed()) {
                    int randomInt = new Random().nextInt(10);
                    if (randomInt % 2 == 0) {
                        e.onComplete();
                    } else {
                        e.onError(new IllegalStateException("Can't complete because an error has occurred."));
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