package com.rxjava2.android.samples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSimpleActivity(View view) {
        startActivity(new Intent(MainActivity.this, SimpleExampleActivity.class));
    }

    public void startMapActivity(View view) {
        startActivity(new Intent(MainActivity.this, MapExampleActivity.class));
    }

    public void startZipActivity(View view) {
        startActivity(new Intent(MainActivity.this, ZipExampleActivity.class));
    }

    public void startDisposableActivity(View view) {
        startActivity(new Intent(MainActivity.this, DisposableExampleActivity.class));
    }

    public void startTakeActivity(View view) {
        startActivity(new Intent(MainActivity.this, TakeExampleActivity.class));
    }

    public void startTimerActivity(View view) {
        startActivity(new Intent(MainActivity.this, TimerExample.class));
    }

    public void startIntervalActivity(View view) {
        startActivity(new Intent(MainActivity.this, IntervalExampleActivity.class));
    }

    public void startSingleObserverActivity(View view) {
        startActivity(new Intent(MainActivity.this, SingleObserverExampleActivity.class));
    }

    public void startCompletableObserverActivity(View view) {
        startActivity(new Intent(MainActivity.this, CompletableObserverActivity.class));
    }

    public void startFlowableActivity(View view) {
        startActivity(new Intent(MainActivity.this, FlowableExampleActivity.class));
    }

    public void startReduceActivity(View view) {
        startActivity(new Intent(MainActivity.this, ReduceExampleActivity.class));
    }

    public void startBufferActivity(View view) {
        startActivity(new Intent(MainActivity.this, BufferExampleActivity.class));
    }

    public void startFilterActivity(View view) {
        startActivity(new Intent(MainActivity.this, FilterExampleActivity.class));
    }

    public void startSkipActivity(View view) {
        startActivity(new Intent(MainActivity.this, SkipExampleActivity.class));
    }

    public void startReplayActivity(View view) {
        startActivity(new Intent(MainActivity.this, ReplayExampleActivity.class));
    }

    public void startConcatActivity(View view) {
        startActivity(new Intent(MainActivity.this, ConcatExampleActivity.class));
    }

    public void startMergeActivity(View view) {
        startActivity(new Intent(MainActivity.this, MergeExampleActivity.class));
    }

    public void startDeferActivity(View view) {
        startActivity(new Intent(MainActivity.this, DeferExampleActivity.class));
    }

    public void startDistinctActivity(View view) {
      startActivity(new Intent(MainActivity.this, DistinctExampleActivity.class));
    }

    public void startLastOperatorActivity(View view){
      startActivity(new Intent(MainActivity.this, LastOperatorExampleActivity.class ));
    }
}
