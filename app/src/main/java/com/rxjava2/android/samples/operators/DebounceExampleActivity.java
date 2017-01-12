package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.AbsExampleActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 22/12/16.
 */

public class DebounceExampleActivity extends AbsExampleActivity {

    private static final String TAG = DebounceExampleActivity.class.getSimpleName();


    /*
    * Using debounce() -> only emit an item from an Observable if a particular time-span has
    * passed without it emitting another item, so it will emit 2, 4, 5 as we have simulated it.
    */
    protected void doSomeWork() {

        //debounce 是发射所有 时间片段 交集 中最后一个元素。
        //这里的时间片段是指每次发射元素的当前时刻+超时时间形成的时间片段
        getObservable()
                .debounce(500, TimeUnit.MILLISECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time wait
                emitter.onNext(1); // skip
                Thread.sleep(400);
                emitter.onNext(2); // deliver
                Thread.sleep(505);
                emitter.onNext(3); // skip
                Thread.sleep(100);
                emitter.onNext(4); // deliver
                Thread.sleep(605);
                emitter.onNext(5); // deliver
                Thread.sleep(510);
                emitter.onComplete();

                /*
                分析如下：
                1和2在400毫秒处有交集，所以1被扔掉。
                2和3之间有500毫秒的间隔，没有交集，所以2被发射出去。
                3在905毫秒出准备发射，但是紧接着4在1005毫秒处也要准备发射，所以3和4有交集，3被扔掉。
                4和5之间有605毫秒的间隔，没有交集，所以4被发射出去。
                5在接下来的500毫秒内没有和其他元素有交集，所以发射出去。
                （如还不明白，建议在纸上画出各个元素的时间片段）
                 */

            }
        });
    }


}