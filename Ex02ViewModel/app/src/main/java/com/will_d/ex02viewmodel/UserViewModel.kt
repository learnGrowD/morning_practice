package com.will_d.ex02viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private var _height = MutableLiveData<Int>()

    val height : LiveData<Int>
    get() = _height

    init {
        _height.value = 170
    }

    fun increas() {
        _height.value = _height.value?.plus(1)
    }

}