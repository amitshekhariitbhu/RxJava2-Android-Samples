package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.ExampleBaseActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class ScanExampleActivity extends ExampleBaseActivity {

    /* Using scan operator, it sends also the previous result */
    protected void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer int1, Integer int2) throws Exception {
                        return int1 + int2;
                    }
                })
                .subscribe(getObserver());
        //Scan又叫累加器。将原始第一个与第二个应用函数的值作为第二个发射出去数据（第一个发射的数据就是原始第一个数据）
        //第三个发射的数据是原始第三个与第二个发射出去的应用函数后的值。


    }

    private Observable<Integer> getObservable() {
        return Observable.just(1, 2, 3, 4, 5);
    }
}