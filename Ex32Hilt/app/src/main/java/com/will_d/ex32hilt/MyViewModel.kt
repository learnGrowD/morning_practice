package com.will_d.ex32hilt

import androidx.lifecycle.ViewModel
import com.will_d.ex32hilt.repository.Repository
import javax.inject.Inject


class MyViewModel @Inject constructor(
    val repository: Repository
) {
    val a = "Hello Android"
    init {
        repository.getCar()
    }
}