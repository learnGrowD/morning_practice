package com.will_d.ex29callbackflowstateflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.will_d.ex29callbackflowstateflow.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val viewmodel by lazy { ViewModelProvider(this)[ViewModel::class.java] }
    val binding : ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    val TAG = "StateFlow"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //stateFlow DataHolder의 역할을 하면서 데이터 스트림의 역할까지 한다.

        //예제#1 stateFlow 기본...
//        binding.btn.setOnClickListener {
//            lifecycleScope.launch {
//                viewmodel.startSendDataToStateFlow()
//            }
//        }
//
//        lifecycleScope.launch {
//            viewmodel.stateFlow.collect {
//                Log.i("StateFlowTest", "StateFlow #1 : $it - ${Thread.currentThread().name}")
//            }
//        }
//        Log.i("StateFlowTest", "State Collect End ${Thread.currentThread().name}")

        //예제#2 stateFlow가 왜 hotStream인지 보여줄게...

//        lifecycleScope.launch {
//            viewmodel.startSendDataToStateFlow()
//        }
//
//
//        lifecycleScope.launch {
//            delay(1100)
//            viewmodel.stateFlow.collect {
//                Log.i(TAG, "stateFlow #1 : $it - ${Thread.currentThread().name}")
//            }
//
//            Log.i(TAG, "state collect End #1 - ${Thread.currentThread().name}")
//        }

        lifecycleScope.launch {
            viewmodel.startSendDataToStateFlow()
        }

        lifecycleScope.launch {
            delay(1100)
            viewmodel.stateFlow.collect {
                Log.i(TAG, "stateFlow #1 : $it - ${Thread.currentThread().name}")
            }
            Log.i(TAG, "state collect End #1 - ${Thread.currentThread().name}")
        }

        lifecycleScope.launch {
            delay(1100)
            viewmodel.stateFlow.collect {
                Log.i(TAG, "stateFlow #2 : $it - ${Thread.currentThread().name}")
            }
            Log.i(TAG, "state collect End #2 - ${Thread.currentThread().name}")
        }



    }
}