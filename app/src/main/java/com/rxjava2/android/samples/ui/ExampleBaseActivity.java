package com.rxjava2.android.samples.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by threshold on 2017/1/11.
 */

public abstract class ExampleBaseActivity extends AppCompatActivity {

    protected String TAG = getClass().getSimpleName();
    protected Button btn;
    protected TextView textView;

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

    /**
     * Do some work on button click.
     */
    protected abstract void doSomeWork();

    protected <T> Observer<T> getObserver() {
        return getObserver("");
    }

    protected <T> Observer<T> getObserver(final Object observerTag) {
        return new Observer<T>() {

            @Override
            public void onSubscribe(Disposable d) {
                ExampleBaseActivity.this.onSubscribe(observerTag, d);
            }

            @Override
            public void onNext(T value) {
                ExampleBaseActivity.this.onNext(observerTag, value);
            }

            @Override
            public void onError(Throwable e) {
                ExampleBaseActivity.this.onError(observerTag, e);
            }

            @Override
            public void onComplete() {
                ExampleBaseActivity.this.onComplete(observerTag);
            }
        };
    }

    protected CompletableObserver getCompletableObserver() {
        return getCompletableObserver("");
    }

    protected CompletableObserver getCompletableObserver(final Object observerTag) {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                ExampleBaseActivity.this.onSubscribe(observerTag, d);
            }

            @Override
            public void onComplete() {
                ExampleBaseActivity.this.onComplete(observerTag);
            }

            @Override
            public void onError(Throwable e) {
                ExampleBaseActivity.this.onError(observerTag, e);
            }
        };
    }

    protected <T> DisposableObserver<T> getDisposableObserver() {
        return getDisposableObserver("");
    }

    protected <T> DisposableObserver<T> getDisposableObserver(final Object observerTag) {
        return new DisposableObserver<T>() {
            @Override
            public void onNext(T t) {
                ExampleBaseActivity.this.onNext(observerTag, t);
            }

            @Override
            public void onError(Throwable e) {
                ExampleBaseActivity.this.onError(observerTag, e);
            }

            @Override
            public void onComplete() {
                ExampleBaseActivity.this.onComplete(observerTag);
            }
        };
    }

    protected <T> SingleObserver<T> getSingleObserver() {
        return getSingleObserver("");
    }

    protected <T> SingleObserver<T> getSingleObserver(final Object observerTag) {
        return new SingleObserver<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                ExampleBaseActivity.this.onSubscribe(observerTag, d);
            }

            @Override
            public void onSuccess(T t) {
                ExampleBaseActivity.this.onNext(observerTag, t);
            }

            @Override
            public void onError(Throwable e) {
                ExampleBaseActivity.this.onError(observerTag, e);
            }
        };
    }

    protected <T> MaybeObserver<T> getMaybeObserver() {
        return getMaybeObserver("");
    }

    protected <T> MaybeObserver<T> getMaybeObserver(final Object observerTag) {
        return new MaybeObserver<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                ExampleBaseActivity.this.onSubscribe(observerTag, d);
            }

            @Override
            public void onSuccess(T t) {
                ExampleBaseActivity.this.onSuccess(observerTag, t);
            }

            @Override
            public void onError(Throwable e) {
                ExampleBaseActivity.this.onError(observerTag, e);
            }

            @Override
            public void onComplete() {
                ExampleBaseActivity.this.onComplete(observerTag);
            }
        };
    }

    protected void onSubscribe(Object observerTag, Disposable d) {
        String msg = observerTag + "  onSubscribe";
        if (d != null) {
            msg = msg + ": isDisposed :" + d.isDisposed();
        }
        Log.d(TAG, msg);
        textView.append(msg);
        textView.append(AppConstant.LINE_SEPARATOR);
    }

    protected <T> void onNext(Object observerTag, T value) {
        onPositive(observerTag, "onNext", value);
    }

    protected <T> void onSuccess(Object observerTag, T value) {
        onPositive(observerTag, "onSuccess", value);
    }

    private <T> void onPositive(Object observerTag, String positiveKey, T value) {
        if (value instanceof List) {
            List valueList = (List) value;
            Log.d(TAG, observerTag + " " + positiveKey + "  size :" + valueList.size());
            textView.append(observerTag + " " + positiveKey + "  size :" + valueList.size());
            textView.append(AppConstant.LINE_SEPARATOR);
            for (Object obj : valueList) {
                Log.d(TAG, " : value : " + obj.toString());
                textView.append(" value : " + obj.toString());
                textView.append(AppConstant.LINE_SEPARATOR);
            }
        } else {
            Log.d(TAG, observerTag + " " + positiveKey + "  value : " + value);
            textView.append(observerTag + " " + positiveKey + " : value : " + value);
            textView.append(AppConstant.LINE_SEPARATOR);
        }
    }

    protected void onError(Object observerTag, Throwable e) {
        textView.append(observerTag + "  onError : " + e.getMessage());
        textView.append(AppConstant.LINE_SEPARATOR);
        Log.e(TAG, observerTag + "  onError : ", e);
    }

    public void onComplete(Object observerTag) {
        textView.append(observerTag + "  onComplete");
        textView.append(AppConstant.LINE_SEPARATOR);
        Log.d(TAG, observerTag + "  onComplete");
    }


}
