package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;


/**
 * Created by amitshekhar on 27/08/16.
 */
public class FilterExampleActivity extends AppCompatActivity {

    private static final String TAG = FilterExampleActivity.class.getSimpleName();
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
     * simple example by using filter operator to emit only even value
     *
     */
    private void doSomeWork() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(getObserver());
    }


    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                textView.append(" onNext : ");
                textView.append(AppConstant.LINE_SEPARATOR);
                textView.append(" value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext ");
                Log.d(TAG, " value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }


}