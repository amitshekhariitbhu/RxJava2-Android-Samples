package com.rxjava2.android.samples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.rxjava2.android.samples.utils.AppConstant;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by techteam on 13/09/16.
 */
public class LastOperatorExampleActivity extends AppCompatActivity {

  private static final String TAG = DistinctExampleActivity.class.getSimpleName();
  Button btn;
  TextView textView;


  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
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

  private void doSomeWork(){
    getObservable().last() .subscribe(getObserver());
  }

  private Observable<String> getObservable() {
    return Observable.just("A1", "A2", "A3", "A4", "A5", "A6");
  }

  private Observer<String> getObserver() {
    return new Observer<String>() {

      @Override
      public void onSubscribe(Disposable d) {
        Log.d(TAG, " onSubscribe : " + d.isDisposed());
      }

      @Override public void onNext(String value) {
        textView.append(" onNext : value : " + value);
        textView.append(AppConstant.LINE_SEPARATOR);
        Log.d(TAG, " onNext value : " + value);
      }


      @Override
      public void onError(Throwable e) {
        Log.d(TAG, " onError : " + e.getMessage());
      }

      @Override
      public void onComplete() {
        Log.d(TAG, " onComplete");
      }
    };
  }
}
