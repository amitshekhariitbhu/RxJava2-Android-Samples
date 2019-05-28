package com.rxjava2.android.samples.ui.operators

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.model.User
import com.rxjava2.android.samples.utils.AppConstant
import com.rxjava2.android.samples.utils.Utils
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * Created by amitshekhar on 27/08/16.
 */
class ZipExampleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ZipExampleActivity"
    }

    private lateinit var btn: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        btn = findViewById(R.id.btn)
        textView = findViewById(R.id.textView)

        btn.setOnClickListener {
            doSomeWork()
        }
    }

    /*
     * Here we are getting two user list
     * One, the list of cricket fans
     * Another one, the list of football fans
     * Then we are finding the list of users who loves both
     */
    private fun doSomeWork() {
        Observable.zip(getCricketFansObservable(), getFootballFansObservable(),
                BiFunction<List<User>, List<User>, List<User>> { cricketFans, footballFans ->
                    return@BiFunction Utils.filterUserWhoLovesBoth(cricketFans, footballFans)
                })
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver())
    }

    private fun getCricketFansObservable(): Observable<List<User>> {
        return Observable.create(ObservableOnSubscribe<List<User>> { e ->
            if (!e.isDisposed) {
                e.onNext(Utils.getUserListWhoLovesCricket())
                e.onComplete()
            }
        }).subscribeOn(Schedulers.io())
    }

    private fun getFootballFansObservable(): Observable<List<User>> {
        return Observable.create(ObservableOnSubscribe<List<User>> { e ->
            if (!e.isDisposed) {
                e.onNext(Utils.getUserListWhoLovesFootball())
                e.onComplete()
            }
        }).subscribeOn(Schedulers.io())
    }

    private fun getObserver(): Observer<List<User>> {
        return object : Observer<List<User>> {

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed)
            }

            override fun onNext(userList: List<User>) {
                textView.append(" onNext")
                textView.append(AppConstant.LINE_SEPARATOR)
                for (user in userList) {
                    textView.append(" firstname : ${user.firstname}")
                    textView.append(AppConstant.LINE_SEPARATOR)
                }
                Log.d(TAG, " onNext : " + userList.size)
            }

            override fun onError(e: Throwable) {
                textView.append(" onError : " + e.message)
                textView.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onError : " + e.message)
            }

            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " onComplete")
            }
        }
    }

}