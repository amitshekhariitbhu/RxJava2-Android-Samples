package com.rxjava2.android.samples.ui.search;

import android.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by amitshekhar on 15/10/17.
 */

public class RxSearchObservable {

    private RxSearchObservable() {
        // no instance
    }

    public static Observable<String> fromView(SearchView searchView) {

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                subject.onNext(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                subject.onNext(text);
                return true;
            }
        });

        return subject;
    }
}
