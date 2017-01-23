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
     * Do some work when click the button.
     */
    protected abstract void doSomeWork() ;

    protected <T> Observer<T> getObserver() {
        return getObserver("");
    }

    protected <T> Observer<T> getObserver(final Object theNumberOfObserver) {
        return new Observer<T>() {

            @Override
            public void onSubscribe(Disposable d) {
                ExampleBaseActivity.this.onSubscribe(theNumberOfObserver,d);
            }

            @Override
            public void onNext(T value) {
                ExampleBaseActivity.this.onNext(theNumberOfObserver,value);
            }

            @Override
            public void onError(Throwable e) {
                ExampleBaseActivity.this.onError(theNumberOfObserver,e);
            }

            @Override
            public void onComplete() {
                ExampleBaseActivity.this.onComplete(theNumberOfObserver);
            }
        };
    }

    protected CompletableObserver getCompletableObserver() {
        return getCompletableObserver("");
    }

    protected CompletableObserver getCompletableObserver(final Object theNumberOfObserver) {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                ExampleBaseActivity.this.onSubscribe(theNumberOfObserver, d);
            }

            @Override
            public void onComplete() {
                ExampleBaseActivity.this.onComplete(theNumberOfObserver);
            }

            @Override
            public void onError(Throwable e) {
                ExampleBaseActivity.this.onError(theNumberOfObserver, e);
            }
        };
    }

    protected <T> DisposableObserver<T> getDisposableObserver() {
        return getDisposableObserver("");
    }

    protected <T> DisposableObserver<T> getDisposableObserver(final Object theNumberOfObserver) {
        return new DisposableObserver<T>() {
            @Override
            public void onNext(T t) {
                ExampleBaseActivity.this.onNext(theNumberOfObserver, t);
            }

            @Override
            public void onError(Throwable e) {
                ExampleBaseActivity.this.onError(theNumberOfObserver, e);
            }

            @Override
            public void onComplete() {
                ExampleBaseActivity.this.onComplete(theNumberOfObserver);
            }
        };
    }

    protected <T> SingleObserver<T> getSingleObserver() {
        return getSingleObserver("");
    }

    protected <T> SingleObserver<T> getSingleObserver(final Object theNumberOfObserver) {
        return new SingleObserver<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                ExampleBaseActivity.this.onSubscribe(theNumberOfObserver, d);
            }

            @Override
            public void onSuccess(T t) {
                ExampleBaseActivity.this.onNext(theNumberOfObserver, t);
            }

            @Override
            public void onError(Throwable e) {
                ExampleBaseActivity.this.onError(theNumberOfObserver, e);
            }
        };
    }

    protected <T> MaybeObserver<T> getMaybeObserver() {
        return getMaybeObserver("");
    }

    protected <T> MaybeObserver<T> getMaybeObserver(final Object theNumberOfObserver) {
        return new MaybeObserver<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                ExampleBaseActivity.this.onSubscribe(theNumberOfObserver, d);
            }

            @Override
            public void onSuccess(T t) {
                ExampleBaseActivity.this.onSuccess(theNumberOfObserver, t);
            }

            @Override
            public void onError(Throwable e) {
                ExampleBaseActivity.this.onError(theNumberOfObserver, e);
            }

            @Override
            public void onComplete() {
                ExampleBaseActivity.this.onComplete(theNumberOfObserver);
            }
        };
    }

    protected void onSubscribe(Object theNumberOfObserver,Disposable d) {
        String msg = theNumberOfObserver + "  onSubscribe";
        if (d != null) {
            msg = msg + ": isDisposed :" + d.isDisposed();
        }
        Log.d(TAG, msg);
        textView.append(msg);
        textView.append(AppConstant.LINE_SEPARATOR);
    }

    protected <T> void onNext(Object theNumberOfObserver,T value) {
        onPositive(theNumberOfObserver,"onNext",value);
    }

    protected <T> void onSuccess(Object theNumberOfObserver, T value) {
        onPositive(theNumberOfObserver,"onSuccess",value);
    }

    private <T> void onPositive(Object theNumberOfObserver,String positiveKey,T value) {
        if (value instanceof List) {
            List valueList = (List) value;
            Log.d(TAG, theNumberOfObserver+" "+positiveKey+"  size :" + valueList.size());
            textView.append( theNumberOfObserver+" "+positiveKey+"  size :" + valueList.size());
            textView.append(AppConstant.LINE_SEPARATOR);
            for (Object obj : valueList) {
                Log.d(TAG, " : value : " + obj.toString());
                textView.append(" value : " + obj.toString());
                textView.append(AppConstant.LINE_SEPARATOR);
            }
        } else {
            Log.d(TAG, theNumberOfObserver+" "+positiveKey+"  value : " + value);
            textView.append(theNumberOfObserver+" "+positiveKey+" : value : " + value);
            textView.append(AppConstant.LINE_SEPARATOR);
        }
    }

    protected  void onError(Object theNumberOfObserver,Throwable e) {
        textView.append(theNumberOfObserver+"  onError : " + e.getMessage());
        textView.append(AppConstant.LINE_SEPARATOR);
        Log.e(TAG, theNumberOfObserver+"  onError : " , e);
    }

    public void onComplete(Object theNumberOfObserver) {
        textView.append(theNumberOfObserver+"  onComplete");
        textView.append(AppConstant.LINE_SEPARATOR);
        Log.d(TAG, theNumberOfObserver+"  onComplete");
    }


}
