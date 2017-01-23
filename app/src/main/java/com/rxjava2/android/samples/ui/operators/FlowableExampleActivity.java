package com.rxjava2.android.samples.ui.operators;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class FlowableExampleActivity extends ExampleBaseActivity {

    private Subscription mSubscription;

    /*
     * simple example using Flowable
     */
    protected void doSomeWork() {

        if (mSubscription != null) {
            mSubscription.cancel();
        }

         Flowable.range(0, 20)
                .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Subscriber<Integer>() {

                    //当订阅后，会首先调用这个方法，其实就相当于onStart()，
                    //传入的Subscription s参数可以用于请求数据或者取消订阅
                    @Override
                    public void onSubscribe(Subscription s) {
                        FlowableExampleActivity.this.onSubscribe("",null);
                        mSubscription = s;
                        //要说明一下，request这个方法若不调用,下游的onNext与OnComplete都不会调用；
                        // 若你写的数量小于真实数据量，只会传你的个数，而且不会调用onComplete方法（毕竟没有传完嘛，当然没有complete）
//                        mSubscription.request(10);
                        mSubscription.request(1);
                    }

                    @Override
                    public void onNext(Integer o) {
//                        SystemClock.sleep(1000);
                        FlowableExampleActivity.this.onNext("",o);
                        mSubscription.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        FlowableExampleActivity.this.onError("",t);
                    }

                    @Override
                    public void onComplete() {
                        FlowableExampleActivity.this.onComplete("");
                    }
                });


//        Flowable<Integer> observable = Flowable.just(1, 2, 3, 4);
//
//        observable.reduce(50, new BiFunction<Integer, Integer, Integer>() {
//            @Override
//            public Integer apply(Integer t1, Integer t2) {
//                return t1 + t2;
//            }
//        }).subscribe(getObserver());

    }


    @Override
    protected void onDestroy() {
        if (mSubscription!=null) {
            mSubscription.cancel();
        }
        super.onDestroy();
//        mCompositeDisposable.dispose();
    }
}