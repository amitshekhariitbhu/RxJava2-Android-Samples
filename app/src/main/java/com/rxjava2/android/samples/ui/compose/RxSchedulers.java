package com.rxjava2.android.samples.ui.compose;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by werockstar on 5/19/2017.
 */

public class RxSchedulers {

    public <T> ObservableTransformer<T, T> applyObservableAsync() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public <T> ObservableTransformer<T, T> applyObservableCompute() {
        return observable -> observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public <T> ObservableTransformer<T, T> applyObservableMainThread() {
        return observable -> observable.observeOn(AndroidSchedulers.mainThread());
    }

    public <T> FlowableTransformer<T, T> applyFlowableMainThread() {
        return flowable -> flowable.observeOn(AndroidSchedulers.mainThread());
    }

    public <T> FlowableTransformer<T, T> applyFlowableAsysnc() {
        return flowable -> flowable.observeOn(AndroidSchedulers.mainThread());
    }

    public <T> FlowableTransformer<T, T> applyFlowableCompute() {
        return flowable -> flowable.observeOn(AndroidSchedulers.mainThread());
    }

}
