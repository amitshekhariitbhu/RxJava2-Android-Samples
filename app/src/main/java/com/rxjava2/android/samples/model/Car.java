package com.rxjava2.android.samples.model;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Created by amitshekhar on 30/08/16.
 */
public class Car {

    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Observable<String> brandDeferObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() {
                return Observable.just(brand);
            }
        });
    }

}
