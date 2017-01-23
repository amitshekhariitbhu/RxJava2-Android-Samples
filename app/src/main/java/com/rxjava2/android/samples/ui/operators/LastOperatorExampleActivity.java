package com.rxjava2.android.samples.ui.operators;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;

import io.reactivex.Observable;

/**
 * Created by techteam on 13/09/16.
 */
public class LastOperatorExampleActivity extends ExampleBaseActivity {

    protected void doSomeWork() {
        getObservable()
                .last("Default") // the default item ("Default") to emit if the source ObservableSource is empty
                .subscribe(this.<String>getSingleObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("A1", "A2", "A3", "A4", "A5", "A6");
    }

}
