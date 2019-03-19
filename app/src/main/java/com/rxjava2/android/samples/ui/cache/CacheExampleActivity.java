package com.rxjava2.android.samples.ui.cache;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.ui.cache.model.Data;
import com.rxjava2.android.samples.ui.cache.source.DataSource;
import com.rxjava2.android.samples.ui.cache.source.DiskDataSource;
import com.rxjava2.android.samples.ui.cache.source.MemoryDataSource;
import com.rxjava2.android.samples.ui.cache.source.NetworkDataSource;
import com.rxjava2.android.samples.utils.AppConstant;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheExampleActivity extends AppCompatActivity {

    private static final String TAG = CacheExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;
    DataSource dataSource;

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

        dataSource = new DataSource(new MemoryDataSource(), new DiskDataSource(), new NetworkDataSource());
    }

    private void doSomeWork() {

        Observable<Data> memory = dataSource.getDataFromMemory();
        Observable<Data> disk = dataSource.getDataFromDisk();
        Observable<Data> network = dataSource.getDataFromNetwork();

        Observable.concat(memory, disk, network)
                .firstElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(getObserver());
    }

    private Observer<Data> getObserver() {
        return new Observer<Data>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Data data) {
                textView.append(" onNext : " + data.source);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext : " + data.source);
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