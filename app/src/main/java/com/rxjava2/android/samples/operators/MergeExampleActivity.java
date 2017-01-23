package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.ExampleBaseActivity;

import io.reactivex.Observable;

/**
 * Created by amitshekhar on 28/08/16.
 */
public class MergeExampleActivity extends ExampleBaseActivity {
    /*
     * Using merge operator to combine Observable : merge does not maintain
     * the order of Observable.
     * It will emit all the 7 values may not be in order
     * Ex - "A1", "B1", "A2", "A3", "A4", "B2", "B3" - may be anything
     */
    protected void doSomeWork() {
        final String[] aStrings = {"A1", "A2", "A3", "A4"};
        final String[] bStrings = {"B1", "B2", "B3"};

        final Observable<String> aObservable = Observable.fromArray(aStrings);
        final Observable<String> bObservable = Observable.fromArray(bStrings);

        Observable.merge(aObservable, bObservable)
                .subscribe(getObserver());
    }

}