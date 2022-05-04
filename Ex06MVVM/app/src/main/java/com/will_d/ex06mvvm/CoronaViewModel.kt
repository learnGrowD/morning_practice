package com.will_d.ex06mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class CoronaViewModel(application: Application) : AndroidViewModel(application) {
    private var newsResult : MutableLiveData<ResultGetSearchNews> = MutableLiveData()
    private var newsItem : ArrayList<NewsItems> = arrayListOf()
    private var repo : Coro

}