package com.rxjava2.android.samples.ui.rxbus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.MyApplication;
import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.model.Events;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 06/02/17.
 */

public class RxBusActivity extends AppCompatActivity {

    public static final String TAG = RxBusActivity.class.getSimpleName();
    TextView textView;
    Button button;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // do not send event after activity has been destroyed
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        disposables.add(((MyApplication) getApplication())
                .bus()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) {
                        if (object instanceof Events.AutoEvent) {
                            textView.setText("Auto Event Received");
                        } else if (object instanceof Events.TapEvent) {
                            textView.setText("Tap Event Received");
                        }
                    }
                }));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApplication) getApplication())
                        .bus()
                        .send(new Events.TapEvent());
            }
        });
    }



}
