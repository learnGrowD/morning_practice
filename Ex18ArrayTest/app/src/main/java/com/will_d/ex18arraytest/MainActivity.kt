package com.will_d.ex18arraytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    val arr : HashMap<String, String> by lazy { HashMap() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.i("arrqwdwq", arr::class.java.name)


    }
}