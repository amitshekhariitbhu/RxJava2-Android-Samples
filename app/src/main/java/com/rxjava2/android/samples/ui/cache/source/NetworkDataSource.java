package com.rxjava2.android.samples.ui.cache.source;

import com.rxjava2.android.samples.ui.cache.model.Data;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * Class to simulate Network DataSource
 */
public class NetworkDataSource {

    public Observable<Data> getData() {
        return Observable.create(new ObservableOnSubscribe<Data>() {
            @Override
            public void subscribe(ObservableEmitter<Data> emitter) throws Exception {
                Data data = new Data();
                data.source = "network";
                emitter.onNext(data);
                emitter.onComplete();
            }
        });
    }

}
