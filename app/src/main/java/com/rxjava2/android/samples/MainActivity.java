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
        startActivity(new Intent(MainActivity.this, SimpleExample.class));
    }

    public void startMapActivity(View view) {
        startActivity(new Intent(MainActivity.this, MapExample.class));
    }

    public void startZipActivity(View view) {
        startActivity(new Intent(MainActivity.this, ZipExample.class));
    }

    public void startDisposableActivity(View view) {
        startActivity(new Intent(MainActivity.this, DisposableExample.class));
    }

    public void startTakeActivity(View view) {
        startActivity(new Intent(MainActivity.this, TakeExample.class));
    }
}
