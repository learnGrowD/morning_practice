package com.will_d.ex04databindingviewmodellivedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    val counter : MutableLiveData<Int> by lazy { MutableLiveData() }

    init {
        counter.value = 0
    }


    fun clickBtn(){
        counter.value = counter.value?.plus(1)
    }
}