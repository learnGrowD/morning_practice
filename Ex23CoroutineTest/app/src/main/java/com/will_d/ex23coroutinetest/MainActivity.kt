package com.will_d.ex23coroutinetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("CoroutineTest#1","start Main Thread")

        //1.
        GlobalScope.launch {
            delay(3000)
            Log.i("CoroutineTest#1","in Coroutine")
        }

        Log.i("CoroutineTest#1","End Main Thread")


        //2.
        GlobalScope.launch {
            launch {
                Log.i("CoroutineTest#2", "At here!")
            }

            val value : Int = async {
                var total = 0
                for (i in 1..10) total += i
                total
            }.await()

            Log.i("CoroutineTest#2", "${value}")

        }

        //3.
        GlobalScope.launch {
            val x = doSomthing()
            Log.i(logName(3), "" + x)
        }

        CoroutineScope(Dispatchers.Main).launch {

        }

    }

    suspend fun doSomthing() : Int{
        val value : Int = GlobalScope.async(Dispatchers.IO) {
            var total = 0
            for (i in 1..10) total += i
            Log.i(logName(3), "do something in a suspend method : ${total}" )
            total
        }.await()

        return value
    }

    fun logName(num : Int) : String{
        return "CoroutineTest#${num}"
    }
}