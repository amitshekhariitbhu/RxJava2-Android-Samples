package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by techteam on 13/09/16.
 */
public class LastOperatorExampleActivity extends AppCompatActivity {

    private static final String TAG = DistinctExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
    * last() emits only the last item emitted by the Observable.
    */
    private void doSomeWork() {
        getObservable().last("A1") // the default item ("A1") to emit if the source ObservableSource is empty
                .subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("A1", "A2", "A3", "A4", "A5", "A6");
    }

    private SingleObserver<String> getObserver() {
        return new SingleObserver<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(String value) {
                textView.append(" onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext value : " + value);
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
