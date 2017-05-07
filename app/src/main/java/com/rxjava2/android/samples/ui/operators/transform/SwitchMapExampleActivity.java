package com.rxjava2.android.samples.ui.operators.transform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.model.ApiUser;
import com.rxjava2.android.samples.model.User;
import com.rxjava2.android.samples.ui.operators.utility.MaterializeExampleActivity;
import com.rxjava2.android.samples.utils.AppConstant;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jant on 2017/4/7.
 */

public class SwitchMapExampleActivity extends AppCompatActivity {


    private static final String TAG = MaterializeExampleActivity.class.getSimpleName();
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


        ApiUser lApiUser = new ApiUser();
        lApiUser.firstname = "a";
        lApiUser.lastname = "a1";
        lApiUser.id = 1;

        User lUser1 = new User(lApiUser);

        lApiUser.firstname = "b";
        lApiUser.lastname = "b1";
        lApiUser.id = 2;

        User lUser2 = new User(lApiUser);


        Observable
                .fromArray(lUser1, lUser2)
                .switchMap(new Function<User, Observable<String>>() {
                    @Override
                    public Observable<String> apply(@NonNull User s) throws Exception {
//                return Observable.just(s).subscribeOn(Schedulers.newThread());
//                        Thread.sleep(2000);
                        return Observable.fromArray(s.firstname, s.lastname,s.firstname, s.lastname,s.firstname, s.lastname
                                ,s.firstname, s.lastname,s.firstname, s.lastname
                                ,s.firstname, s.lastname,s.firstname, s.lastname
                                ,s.firstname, s.lastname,s.firstname, s.lastname
                                ,s.firstname, s.lastname,s.firstname, s.lastname,"oooo").subscribeOn(Schedulers.newThread());
                    }

                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        textView.append(" accept：" + s);
                        textView.append(AppConstant.LINE_SEPARATOR);
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
