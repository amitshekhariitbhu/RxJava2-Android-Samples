package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by Jant on 2017/4/6.
 */

public class MeterailizeExampleActivity extends AppCompatActivity {


    private static final String TAG = MeterailizeExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork();
            }
        });
    }


    /**
     * 使用meterailize后，不管是onNext还是onComplete还是onError，都会包装成为Observable发射，
     * Dematerialize的作用则是相反。
     * meterailize会把Observable数据源包装为一个Notification类型再去传递，
     */
    protected void doSomeWork() {
        meterailize();
//        demeterailize();
    }


    private void meterailize() {
        Observable
                .range(10, 3)
                .map(new Function<Integer, Notification<Integer>>() {
                    @Override
                    public Notification<Integer> apply(@NonNull Integer integer) throws Exception {
                        return Notification.createOnNext(integer);
                    }
                })
                .materialize()
                .subscribe(new Observer<Notification<Notification<Integer>>>() {

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        textView.append("onCompleted");
                        textView.append(AppConstant.LINE_SEPARATOR);
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Notification<Notification<Integer>> notification) {

                        textView.append(" materialize：" + notification);
                        textView.append(AppConstant.LINE_SEPARATOR);
                        Log.i(TAG, "materialize" + notification);
                    }
                });
    }

    private void demeterailize() {
        Flowable
                .range(10, 3)
                .map(new Function<Integer, Notification<Integer>>() {
                    @Override
                    public Notification<Integer> apply(@NonNull Integer integer) throws Exception {
                        return Notification.createOnNext(integer);
                    }

                })
                .dematerialize()
                .subscribe(new Subscriber<Object>() {

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onCompleted");
                    }


                    @Override
                    public void onSubscribe(Subscription s) {
                    }

                    @Override
                    public void onNext(Object notification) {
                        Log.i(TAG, "dematerialize = " + notification);
                    }
                });

    }


}
