package com.will_d.ex13staticandsingleton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SecondActivity : AppCompatActivity() {

    val btn : Button by lazy { findViewById(R.id.btn) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btn.setOnClickListener{
            var c = CompanionObjectMemoryTest.Companion
            Log.i("testCode", "AA : " + System.identityHashCode(c))
        }


    }
}