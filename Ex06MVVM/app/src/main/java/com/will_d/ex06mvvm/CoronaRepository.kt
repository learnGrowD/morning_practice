package com.will_d.ex06mvvm

import android.app.Application
import android.database.Observable
import androidx.lifecycle.MutableLiveData

class CoronaRepository(application : Application) {

    private val api = NaverAPI.create()
    private var newsData : MutableLiveData<ResultGetSearchNews> = MutableLiveData()

    fun getNews(n : Int) : Observable<ResultGetSearchNews> = api
        .getSearchNews("코로나 부산", 100, n)
        .sub
}