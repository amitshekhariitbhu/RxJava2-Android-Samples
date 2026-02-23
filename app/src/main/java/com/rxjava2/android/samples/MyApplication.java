package com.rxjava2.android.samples;

import android.annotation.SuppressLint;
import android.app.Application;

import com.rxjava2.android.samples.model.Events;
import com.rxjava2.android.samples.ui.rxbus.RxBus;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by threshold on 2017/1/12.
 */

public class MyApplication extends Application {

    public static final String TAG = "MyApplication";
    private RxBus rxBus;

    @Override
    public void onCreate() {
        super.onCreate();
        rxBus = new RxBus();
    }

    public RxBus getRxBus() {
        return rxBus;
    }

    @SuppressLint("CheckResult")
    public void sendAutoEvent() {
        //noinspection ResultOfMethodCallIgnored
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(aLong -> rxBus.send(new Events.AutoEvent()));
    }

}
