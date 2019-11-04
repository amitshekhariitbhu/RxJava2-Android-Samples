package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class FlowableExampleActivity extends AppCompatActivity {

    private static final String TAG = FlowableExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork();
            }
        });
    }

    /*
     * simple example using Flowable
     */
    private void doSomeWork() {
        /* FLOWABLE
         *   Flowable is like observable but flowable has back pressure so it can handle situations
         *   that creation events are faster than consuming them.
         *   Backpressure is when your observable (publisher) is creating more events than
         *   your subscriber can handle.
         *   application : touch screen, network accesor ,...
         */

        /* JUST
         *   just is an operator in RxJava that coverts an item into an observable that emits the item.
         *   you can pass maximally 10 parameters to just.
         *   if you pass null in just what happen?empty observable or an observable that emits null
         *   as an item?
         *   answer:second one.
         */

        Flowable<Integer> observable = Flowable.just(1, 2, 3, 4);

        /*REDUCE
         *   The Reduce operator applies a function to the first item emitted by the source Observable
         *   and then feeds the result of the function back into the function along with the second
         *   item emitted by the source Observable
         *   reduce has two implementations with one parameter 1)seed and two parameter
         *   1)seed and 2)BiFunction.
         *   BiFunction: is an interface for doing some things on two things and return result or
         *   throw an exception
         */

        observable.reduce(50, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) {
                return t1 + t2;
            }
        }).subscribe(getObserver());

    }
    /*SingleObserver
     *   Subscribing a SingleObserver to multiple SingleSources is not recommended.
     *   The Single class implements the SingleSource base interface and the default consumer type
     *   it interacts with is the SingleObserver via the subscribe(SingleObserver) method.
     */

    private SingleObserver<Integer> getObserver() {

        return new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(Integer value) {
                textView.append(" onSuccess : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onSuccess : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }
        };
    }
}