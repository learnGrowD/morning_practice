package com.will_d.ex24coroutinetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.will_d.ex24coroutinetest.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    val binding : ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        //coroutine의 특징
//        //1. coroutine은 코드 실행 중 멈출 수 있고(suspendable) 다시 실행 가능(resume)
//        //2. 코드 간결화, 효율적, 처리속도 빠름 (메모리 효율적 사용, context switch 비용 절약, lightweight thread)
//
//        //예제#1 -> GlobalScope, delay, Thread 동작
//        GlobalScope.launch {
//            delay(3000L)
//            Log.i(TAG, "tread1 ${Thread.currentThread().name}")
//        }
//
//        Log.i(TAG, "thread2 ${Thread.currentThread().name}")
//
//        //예제#2 suspend fun
//        GlobalScope.launch(Dispatchers.Main) {
//            Log.i(TAG + 2, "1")
//
//            delay(3000L)
//            Log.i(TAG + 2, "Coroutine says hello from thread1 ${Thread.currentThread().name}")
//
//            Log.i(TAG + 2, "2")
//
//            doNetworkCall()
//            Log.i(TAG + 2, "3")
//
//            doNetworkCall2()
//            Log.i(TAG + 2, "4")
//
//        }
//
//        Log.i(TAG + 2, "5")
//
//
//        //예제#3 : MainThread, IOThread, DefaultThread 말고 직접 Thread 할당 해주기..
//
//        GlobalScope.launch(newSingleThreadContext("MyThread")) {
//            Log.i(TAG + 3, Thread.currentThread().name)
//        }
//
//        //예제#4 : withContext를 통해서 Coroutine block내의 Thread 전환 가능..
//        GlobalScope.launch(Dispatchers.IO) {
//            Log.i(TAG + 4, "tread1 name : ${Thread.currentThread().name}")
//            val answer = doNetworkCall()
//
//            withContext(Dispatchers.Main){
//                Log.i(TAG + 4, "tread2 name : ${Thread.currentThread().name}")
//                binding.tv.text = answer
//            }
//        }
//
//        //예제#5 : runBlocking -> CoroutineScope 중 한종류... Thread를 blocking하는 CoroutineScope로 이해하면 될거같다.
//        //CoroutineScope란? -> Coroutine 블락을 제어하는 객체..
//        //참고 Corutine runBlocking을 사용하게 되면 MainThrea에 영향을 줌 MainThread를 사용하는 Coroutine에게도 영향을 줌...
//        //runBlocking이 끝나고 다시 실행한다면 우선순위가 첫째로 MainThread 그다음 차례대로 Coroutine이 우선순위 인것으로 판단됨...
//        GlobalScope.launch(Dispatchers.Main) {
//            Log.i(TAG + 5, "1a")
//            delay(1000L)
//            Log.i(TAG + 5, "2a")
//        }
//        Log.i(TAG + 5, "3a")
//
//        runBlocking {
//            Log.i(TAG + 5, "1b")
//            delay(1000L) //여기서 delay를 시키면 UI업데이트 모두 block 하게 됨...
//            Log.i(TAG + 5, "2b")
//        }
//
//        Log.i(TAG + 5, "3b")
//
//        //예제#6 : runBlocking과 Thread.sleep() runBlocking을 사용하는 이유.. -> 결국 runBlocking도 CoroutineScope중 한 종류 즉 Scope내에서는 여러 Thread 실행 및 전환이 가능함.., 그리고 Coroutine의 이점을 가져갈 수 있음
//        runBlocking {
//            launch(Dispatchers.IO) {
//                delay(3000L)
//                Log.i(TAG + 6, "1a : Thread Name : ${Thread.currentThread().name}")
//            }
//
//            launch(Dispatchers.IO) {
//                delay(3000L)
//                Log.i(TAG + 6, "1b : Thread Name : ${Thread.currentThread().name}")
//            }
//        }
//        //예제#7 : join, Repeat 사용법
//        val job = GlobalScope.launch(Dispatchers.Default) {
//            repeat(5) {
//                Log.i(TAG + 7, "Coroutine is still working...")
//                delay(1000L)
//            }
//        }
//
//        runBlocking {
//            job.join()
//            Log.i(TAG + 7, "Main thread is continuing")
//        }
//        Log.i(TAG + 7, "DONE")


        //예제#8 cancel
        val job2 = GlobalScope.launch(Dispatchers.Default) {
            repeat(5){ //it will repeat 5 times
                Log.d(TAG + 8,"Coroutine is still working...")
                delay(4000L)
            }


        }

        runBlocking {
            delay(5000L) //2초 delay
            job2.cancel()// 2초 딜레이후 job을 멈추게합니다.
            Log.d(TAG + 8,"Main thread is continuing...")
        }


    }

    suspend fun  doNetworkCall() : String {
        delay(3000L)
        return "this is the answer"
    }

    suspend fun doNetworkCall2() : String {
        delay(3000L)
        return "this is the answer"
    }
}