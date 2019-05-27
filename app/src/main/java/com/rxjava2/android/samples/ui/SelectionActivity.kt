package com.rxjava2.android.samples.ui

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity

import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.ui.networking.NetworkingActivity
import com.rxjava2.android.samples.ui.search.SearchActivity

class SelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
    }

    fun startOperatorsActivity(view: View) {
        startActivity(Intent(this@SelectionActivity, OperatorsActivity::class.java))
    }

    fun startNetworkingActivity(view: View) {
        startActivity(Intent(this@SelectionActivity, NetworkingActivity::class.java))
    }

    fun startSearchActivity(view: View) {
        startActivity(Intent(this@SelectionActivity, SearchActivity::class.java))
    }

}