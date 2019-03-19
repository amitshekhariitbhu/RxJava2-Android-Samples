package com.rxjava2.android.samples.ui.operators;

import android.util.Log;

import com.rxjava2.android.samples.utils.AppConstant;
import com.rxjava2.android.samples.utils.ObserverAdapter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;

public class TakeUntilExampleActivity extends TakeOperatorBaseActivity {

    private static final String TAG = TakeWhileExampleActivity.class.getSimpleName();

    @Override
    protected void doSomeWork() {
        Observable<Long> timerObservable = Observable.timer(5, TimeUnit.SECONDS);
        timerObservable.subscribe(new ObserverAdapter<Long>() {
            @Override
            public void onComplete() {
                String print = " Timer completed";
                textView.append(print);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, print);
            }
        });

        getStringObservable()
                //Delay item emission by one second
                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), new BiFunction<String, Long, String>() {
                    @Override
                    public String apply(String s, Long aLong) throws Exception {
                        return s;
                    }
                })
                //Will receive the items from Strings observable until timerObservable doesn't start emitting data.
                .takeUntil(timerObservable)
                //We need to observe on MainThread because delay works on background thread to avoid UI blocking.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }
}
