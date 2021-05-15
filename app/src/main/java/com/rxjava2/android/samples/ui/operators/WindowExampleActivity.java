package com.rxjava2.android.samples.ui.operators;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WindowExampleActivity extends AppCompatActivity {

    private static final String TAG = WindowExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(view -> doSomeWork());
    }

    /*
     * Example using window operator -> It periodically
     * subdivide items from an Observable into
     * Observable windows and emit these windows rather than
     * emitting the items one at a time
     */
    @SuppressLint("CheckResult")
    protected void doSomeWork() {
        //noinspection ResultOfMethodCallIgnored
        Observable.interval(1, TimeUnit.SECONDS)
                .take(12)
                .window(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getConsumer());
    }

    @SuppressLint("CheckResult")
    public Consumer<Observable<Long>> getConsumer() {
        return observable -> {
            Log.d(TAG, "Sub Divide begin....");
            textView.append("Sub Divide begin ....");
            textView.append(AppConstant.LINE_SEPARATOR);
            //noinspection ResultOfMethodCallIgnored
            observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(value -> {
                        Log.d(TAG, "Next:" + value);
                        textView.append("Next:" + value);
                        textView.append(AppConstant.LINE_SEPARATOR);
                    });
        };
    }
}
