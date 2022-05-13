package com.will_d.ex29callbackflowstateflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

class ViewModel : ViewModel() {
    val stateFlow = MutableStateFlow(99)

    suspend fun startSendDataToStateFlow() {
        repeat(10) {
            stateFlow.value = it
            delay(500)
        }
    }
}