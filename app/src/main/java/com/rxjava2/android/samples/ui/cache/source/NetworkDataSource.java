package com.rxjava2.android.samples.ui.cache.source;

import com.rxjava2.android.samples.ui.cache.model.Data;

import io.reactivex.Observable;


/**
 * Class to simulate Network DataSource
 */
public class NetworkDataSource {

    public Observable<Data> getData() {
        return Observable.create(emitter -> {
            Data data = new Data();
            data.source = "network";
            emitter.onNext(data);
            emitter.onComplete();
        });
    }

}
