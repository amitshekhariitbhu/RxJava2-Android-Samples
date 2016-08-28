package com.rxjava2.android.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class ConcatExampleActivity extends AppCompatActivity {

    private static final String TAG = ConcatExampleActivity.class.getSimpleName();
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
     * Using concat operator to combine Observable : concat maintain
     * the order.
     * It will emit all the 7 values in order
     * here - first 1, 3, 5, 7 and then 2, 4, 6
     * first all from the first Observable and then
     * all from the second Observable all in order
     */
    private void doSomeWork() {
        final String[] oddStrings = {"1", "3", "5", "7"};
        final String[] evenStrings = {"2", "4", "6"};

        final Observable<String> oddObservable = Observable.fromArray(oddStrings);
        final Observable<String> evenObservable = Observable.fromArray(evenStrings);

        Observable.concat(oddObservable, evenObservable)
                .subscribe(getObserver());
    }


    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                textView.append(" onSubscribe : isDisposed :" + d.isDisposed());
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                textView.append(" onNext : value : " + value);
                Log.d(TAG, " onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : " + e.getMessage());
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                Log.d(TAG, " onComplete");
            }
        };
    }


}