package com.rxjava2.android.samples.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.ui.operators.CompletableObserverExampleActivity;
import com.rxjava2.android.samples.ui.operators.CompositeDisposableActivity;
import com.rxjava2.android.samples.ui.operators.FlowableExampleActivity;
import com.rxjava2.android.samples.ui.operators.ObservableExampleActivity;
import com.rxjava2.android.samples.ui.operators.SingleObserverExampleActivity;
import com.rxjava2.android.samples.ui.operators.combine.CombineLatestActivity;
import com.rxjava2.android.samples.ui.operators.combine.MergeExampleActivity;
import com.rxjava2.android.samples.ui.operators.combine.ZipExampleActivity;
import com.rxjava2.android.samples.ui.operators.connectable.ReplayExampleActivity;
import com.rxjava2.android.samples.ui.operators.create.DeferExampleActivity;
import com.rxjava2.android.samples.ui.operators.create.IntervalExampleActivity;
import com.rxjava2.android.samples.ui.operators.create.TimerExampleActivity;
import com.rxjava2.android.samples.ui.operators.filter.DebounceExampleActivity;
import com.rxjava2.android.samples.ui.operators.filter.DistinctExampleActivity;
import com.rxjava2.android.samples.ui.operators.filter.FilterExampleActivity;
import com.rxjava2.android.samples.ui.operators.filter.LastOperatorExampleActivity;
import com.rxjava2.android.samples.ui.operators.filter.SkipExampleActivity;
import com.rxjava2.android.samples.ui.operators.filter.TakeExampleActivity;
import com.rxjava2.android.samples.ui.operators.filter.ThrottleFirstExampleActivity;
import com.rxjava2.android.samples.ui.operators.filter.ThrottleLastExampleActivity;
import com.rxjava2.android.samples.ui.operators.mathematical.ConcatExampleActivity;
import com.rxjava2.android.samples.ui.operators.mathematical.ReduceExampleActivity;
import com.rxjava2.android.samples.ui.operators.transform.BufferExampleActivity;
import com.rxjava2.android.samples.ui.operators.transform.MapExampleActivity;
import com.rxjava2.android.samples.ui.operators.transform.ScanExampleActivity;
import com.rxjava2.android.samples.ui.operators.transform.SwitchMapExampleActivity;
import com.rxjava2.android.samples.ui.operators.transform.WindowExampleActivity;
import com.rxjava2.android.samples.ui.operators.utility.DelayExampleActivity;
import com.rxjava2.android.samples.ui.operators.utility.MaterializeExampleActivity;
import com.rxjava2.android.samples.ui.subject.AsyncSubjectExampleActivity;
import com.rxjava2.android.samples.ui.subject.BehaviorSubjectExampleActivity;
import com.rxjava2.android.samples.ui.subject.PublishSubjectExampleActivity;
import com.rxjava2.android.samples.ui.subject.ReplaySubjectExampleActivity;

public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
    }

    public void startSimpleActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ObservableExampleActivity.class));
    }

    public void startMapActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, MapExampleActivity.class));
    }

    public void startZipActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ZipExampleActivity.class));
    }

    public void startDisposableActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, CompositeDisposableActivity.class));
    }

    public void startTakeActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, TakeExampleActivity.class));
    }

    public void startTimerActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, TimerExampleActivity.class));
    }

    public void startIntervalActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, IntervalExampleActivity.class));
    }

    public void startSingleObserverActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, SingleObserverExampleActivity.class));
    }

    public void startCompletableObserverActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, CompletableObserverExampleActivity.class));
    }

    public void startFlowableActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, FlowableExampleActivity.class));
    }

    public void startReduceActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ReduceExampleActivity.class));
    }

    public void startBufferActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, BufferExampleActivity.class));
    }

    public void startFilterActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, FilterExampleActivity.class));
    }

    public void startSkipActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, SkipExampleActivity.class));
    }

    public void startScanActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ScanExampleActivity.class));
    }

    public void startReplayActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ReplayExampleActivity.class));
    }

    public void startConcatActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ConcatExampleActivity.class));
    }

    public void startMergeActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, MergeExampleActivity.class));
    }

    public void startDeferActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, DeferExampleActivity.class));
    }

    public void startDistinctActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, DistinctExampleActivity.class));
    }

    public void startLastOperatorActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, LastOperatorExampleActivity.class));
    }

    public void startReplaySubjectActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ReplaySubjectExampleActivity.class));
    }

    public void startPublishSubjectActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, PublishSubjectExampleActivity.class));
    }

    public void startBehaviorSubjectActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, BehaviorSubjectExampleActivity.class));
    }

    public void startAsyncSubjectActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, AsyncSubjectExampleActivity.class));
    }

    public void startThrottleFirstActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ThrottleFirstExampleActivity.class));
    }

    public void startThrottleLastActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, ThrottleLastExampleActivity.class));
    }

    public void startDebounceActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, DebounceExampleActivity.class));
    }

    public void startWindowActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, WindowExampleActivity.class));
    }

    public void startDelayActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, DelayExampleActivity.class));
    }

    public void startMeterailizeExampleActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, MaterializeExampleActivity.class));
    }


    public void startSwitchMapExampleActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, SwitchMapExampleActivity.class));
    }

    public void startCombineLatestActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, CombineLatestActivity.class));
    }


}
