package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.AbsExampleActivity;

import io.reactivex.Observable;

/**
 * Created by techteam on 13/09/16.
 */
public class LastOperatorExampleActivity extends AbsExampleActivity {

    protected void doSomeWork() {
        getObservable().last("ADefault") // the default item ("ADefault") to emit if the source ObservableSource is empty
                .subscribe(this.<String>getSingleObserver());
        //经过Last操作后，Observable变成SingleObservable了
    }

    private Observable<String> getObservable() {
//        return Observable.empty();
        return Observable.just("A1", "A2", "A3", "A4", "A5", "A6");
    }


}
