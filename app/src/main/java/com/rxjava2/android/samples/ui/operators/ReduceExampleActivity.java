package com.rxjava2.android.samples.ui.operators;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class ReduceExampleActivity extends ExampleBaseActivity {
    /*
     * simple example using reduce to add all the number
     */
    protected void doSomeWork() {
        getObservable()
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer t1, Integer t2) {
                        return t1 + t2;
                    }
                })
                .subscribe(this.<Integer>getMaybeObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.just(1, 2, 3, 4);
    }


}