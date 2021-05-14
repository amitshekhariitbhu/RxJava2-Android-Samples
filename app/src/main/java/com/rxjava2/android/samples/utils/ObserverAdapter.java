package com.rxjava2.android.samples.utils;

import androidx.annotation.NonNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Adapter class for Observer to get the method only needed by the user. Instead of overriding each and every method.
 *
 * @param <T>
 */
public abstract class ObserverAdapter<T> implements Observer<T> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T s) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
