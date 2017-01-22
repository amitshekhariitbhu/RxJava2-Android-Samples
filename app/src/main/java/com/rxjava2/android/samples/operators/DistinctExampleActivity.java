package com.rxjava2.android.samples.operators;

import com.rxjava2.android.samples.AbsExampleActivity;

import io.reactivex.Observable;

/**
 * Created by techteam on 13/09/16.
 */
public class DistinctExampleActivity extends AbsExampleActivity {

  protected void doSomeWork(){
     getObservable().distinct() .subscribe(getObserver());
  }

  private Observable<Integer> getObservable() {
    return Observable.just(1, 2, 1, 1, 2, 3, 4 ,6, 4);
  }

}
