package com.will_d.ex16activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener{
           startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("lifeCycleTest", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.i("lifeCycleTest", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.i("lifeCycleTest", "onResume")
    }
}