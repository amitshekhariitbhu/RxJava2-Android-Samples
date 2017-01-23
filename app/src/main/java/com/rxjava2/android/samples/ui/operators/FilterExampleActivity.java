package com.rxjava2.android.samples.ui.operators;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class FilterExampleActivity extends ExampleBaseActivity {

    /*
     * simple example by using filter operator to emit only even value
     *
     */
    protected void doSomeWork() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(getObserver());
    }

}