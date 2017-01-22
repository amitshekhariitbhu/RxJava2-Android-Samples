package com.rxjava2.android.samples;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by threshold on 2017/1/12.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d("MyApplication","isArm64:" + (Build.SUPPORTED_64_BIT_ABIS.length > 0));
            Observable.fromArray(Build.SUPPORTED_64_BIT_ABIS)
                    .forEach(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            Log.d("MyApplication", s);
                        }
                    });
        }
    }
}
