package com.rxjava2.android.samples.ui.operators;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;

import io.reactivex.Observable;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class BufferExampleActivity extends ExampleBaseActivity {

    /*
     * simple example using buffer operator - bundles all emitted values into a list
     */
    protected void doSomeWork() {


        // 3 means,  it takes max of three from its start index and create list
        // 1 means, it jumps one step every time
        // so the it gives the following list
        // 1 - one, two, three
        // 2 - two, three, four
        // 3 - three, four, five
        // 4 - four, five
        // 5 - five

        getObservable().buffer(3, 1)
                .subscribe(getObserver());

    }

    private Observable<String> getObservable() {
        return Observable.just("one", "two", "three", "four", "five");
    }



}