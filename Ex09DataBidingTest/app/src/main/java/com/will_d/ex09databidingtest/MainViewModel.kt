package com.will_d.ex09databidingtest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application, val itemRepository: ItemRepository) : AndroidViewModel(application) {

    fun getMutableLiveDataItemsList() : LiveData<Item>{
        return itemRepository.
    }
}