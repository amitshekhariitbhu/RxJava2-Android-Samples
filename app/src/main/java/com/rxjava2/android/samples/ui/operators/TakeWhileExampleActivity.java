package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;
import com.rxjava2.android.samples.utils.ObserverAdapter;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Predicate;

public class TakeWhileExampleActivity extends AppCompatActivity {

    private static final String TAG = TakeWhileExampleActivity.class.getSimpleName();

    private Button btn;

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(view -> {
            doSomeWork();
        });
    }

    private void doSomeWork() {
        getStringObservable()
                //Delay item emission by one second
                .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), new BiFunction<String, Long, String>() {
                    @Override
                    public String apply(String s, Long aLong) throws Exception {
                        return s;
                    }
                })
                //Take the items until the condition is met.
                .takeWhile(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return !s.toLowerCase().contains("honey");
                    }
                })
                //We need to observe on MainThread because delay works on background thread to avoid UI blocking.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observer<? super String> getObserver() {
        return new ObserverAdapter<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                textView.append(" onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext value : " + value);
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }


    private Observable<String> getStringObservable() {
        return Observable.just("Alpha", "Beta", "Cupcake", "Doughnut", "Eclair", "Froyo", "GingerBread",
                "Honeycomb", "Ice cream sandwich");
    }

}
