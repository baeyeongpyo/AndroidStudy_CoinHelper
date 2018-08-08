package com.example.yeongpyo.androidStudyCoinHelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.yeongpyo.androidStudyCoinHelper.Adapter.CustomPagerAdater
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "ssss"

        vpContent.adapter = CustomPagerAdater(supportFragmentManager)


    }
}


