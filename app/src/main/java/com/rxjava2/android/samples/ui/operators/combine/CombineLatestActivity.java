package com.rxjava2.android.samples.ui.operators.combine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * Created by Jant on 2017/4/18.
 */

public class CombineLatestActivity extends AppCompatActivity{
    private static final String TAG = CombineLatestActivity.class.getSimpleName();
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

    /*
     * Using merge operator to combine Observable : merge does not maintain
     * the order of Observable.
     * It will emit all the 7 values may not be in order
     * Ex - "A1", "B1", "A2", "A3", "A4", "B2", "B3" - may be anything
     */
    private void doSomeWork() {


        Observable<String> o1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("o1");
                e.onNext("o2");
                e.onNext("o3");
            }
        });
        Observable<String> o2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("o4");
                e.onNext("o5");
                e.onNext("o6");
            }
        });



        Observable.combineLatest(o2, o1, new BiFunction<String, String, Object>() {
            @Override
            public Object apply(@NonNull String s, @NonNull String s2) throws Exception {
                Log.e("combine --- >", "s = " + s + " | s2 = " + s2);
                return s + s2;
            }
        }).subscribe(new Observer<Object>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
