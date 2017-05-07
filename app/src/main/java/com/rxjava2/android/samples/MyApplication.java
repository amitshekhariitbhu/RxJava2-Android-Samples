package com.rxjava2.android.samples;

import android.app.Application;

import com.rxjava2.android.samples.ui.rxbus.RxBus;

/**
 * Created by threshold on 2017/1/12.
 */

public class MyApplication extends Application {

    public static final String TAG = "MyApplication";
    private RxBus bus;

    @Override
    public void onCreate() {
        super.onCreate();
//        bus = new RxBus();
    }

    public RxBus bus() {
        return bus;
    }

    public void sendAutoEvent() {
//        Observable.timer(2, TimeUnit.SECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        bus.send(new Events.AutoEvent());
//                    }
//                });
    }

}
