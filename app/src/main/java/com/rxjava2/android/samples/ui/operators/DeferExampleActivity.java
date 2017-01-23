package com.rxjava2.android.samples.ui.operators;

import com.rxjava2.android.samples.ui.ExampleBaseActivity;
import com.rxjava2.android.samples.model.Car;

import io.reactivex.Observable;

/**
 * Created by amitshekhar on 30/08/16.
 */
public class DeferExampleActivity extends ExampleBaseActivity {

    /*
     * Defer used for Deferring Observable code until subscription in RxJava
     */
    protected void doSomeWork() {

        Car car = new Car();

//        car.setBrand("Audi");

        Observable<String> brandDeferObservable = car.brandDeferObservable();

        car.setBrand("BMW");  // Even if we are setting the brand after creating Observable
                              // we will get the brand as BMW.
                              // If we had not used defer, we would have got null as the brand.

        brandDeferObservable
                .subscribe(getObserver());
    }


}