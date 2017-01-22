package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.AbsExampleActivity;

import io.reactivex.Observable;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class BufferExampleActivity extends AbsExampleActivity {

    /*
     * simple example using buffer operator - bundles all emitted values into a list
     */
    protected void doSomeWork() {

    /*

        Observable<List<String>> buffered = getObservable().buffer(3, 1);

        // 3 means,  it takes max of three from its start index and create list
        // 1 means, it jumps one step every time
        // so the it gives the following list
        // 1 - one, two, three
        // 2 - two, three, four
        // 3 - three, four, five
        // 4 - four, five
        // 5 - five

        buffered.subscribe(getObserver());

        */

       /*
        //每次取2个  每次跳过3个
        //第一次：one、two
        //第二次：four、five
        getObservable().buffer(2, 3)
                .subscribe(getObserver());
        */

        //每次取3个，每次跳过2个
        //第一次：one、two、three
        //第二次：three、four、five
        //第三次：five
        getObservable().buffer(3, 2)
                .subscribe(getObserver());

    }

    private Observable<String> getObservable() {
        return Observable.just("one", "two", "three", "four", "five");
    }



}