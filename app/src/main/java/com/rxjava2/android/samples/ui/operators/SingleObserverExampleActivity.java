package com.rxjava2.android.samples.ui.operators;

import android.util.Log;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class SingleObserverExampleActivity extends ExampleBaseActivity {

    /*
     * simple example using SingleObserver
     */
    protected void doSomeWork() {
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onSuccess("Hello Success!");
//                    e.onError(new RuntimeException("Occur RuntimeException"));
//                    throw new RuntimeException("Occur RuntimeException");
                }
            }
        }).doOnSuccess(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "doOnSuccess: " + s);
            }
        }).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "doOnError: " + throwable.getMessage());
            }
        }).onErrorReturn(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) throws Exception {
                return "Exception message: "+throwable.getMessage();
            }
        }).subscribe(getSingleObserver());
//        Single.just("Amit")
//                .subscribe(getSingleObserver());
    }

}