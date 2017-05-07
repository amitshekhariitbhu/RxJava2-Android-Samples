package com.rxjava2.android.samples.ui.rxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.model.Events;
import com.rxjava2.android.samples.model.StudentEvents;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
        disposables.dispose();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        final Disposable lDisposable =RxBus.getDefault()
                .toObservable(StudentEvents.class)
                .map(new Function<StudentEvents, String>() {

                    @Override
                    public String apply(@NonNull StudentEvents studentEvents) throws Exception {
                        return studentEvents.getName();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String object) throws Exception {
                        Log.i(TAG, "lDisposable  "+object);
                    }
                });

        final Disposable lDisposable1 =RxBus.getDefault()
                .toObservable(Events.class)
                .map(new Function<Events, String>() {

                    @Override
                    public String apply(@NonNull Events studentEvents) throws Exception {
                        return "123";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String object) throws Exception {
                        Log.i(TAG, "lDisposable1  "+object);
                    }
                });


        disposables.add(lDisposable);
        disposables.add(lDisposable1);

//        disposables.add(((MyApplication) getApplication())
//                .bus()
//                .toObservable()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object object) throws Exception {
//                        if (object instanceof Events.AutoEvent) {
//                            textView.setText("Auto Event Received");
//                        } else if (object instanceof Events.TapEvent) {
//                            textView.setText("Tap Event Received");
//                        }
//                    }
//                }));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RxBus.getDefault().post(new StudentEvents(0,"jant"));
//                disposables.delete(lDisposable);
                disposables.remove(lDisposable);
            }
        });
    }

}
