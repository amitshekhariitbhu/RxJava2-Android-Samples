package com.rxjava2.android.samples.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.utils.getQueryTextChangeObservable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by amitshekhar on 15/10/17.
 */
@SuppressLint("CheckResult")
class SearchActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SearchActivity"
    }

    private lateinit var searchView: SearchView
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchView = findViewById(R.id.searchView)
        textViewResult = findViewById(R.id.textViewResult)
        setUpSearchObservable()
    }

    private fun setUpSearchObservable() {
        searchView.getQueryTextChangeObservable()
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter { text ->
                    if (text.isEmpty()) {
                        textViewResult.text = ""
                        return@filter false
                    } else {
                        return@filter true
                    }
                }
                .distinctUntilChanged()
                .switchMap { query ->
                    dataFromNetwork(query)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result ->
                    textViewResult.text = result
                }
    }

    /**
     * Simulation of network data
     */
    private fun dataFromNetwork(query: String): Observable<String> {
        return Observable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map {
                    query
                }
    }

}
