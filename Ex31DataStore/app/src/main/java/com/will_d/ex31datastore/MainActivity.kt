package com.will_d.ex31datastore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.will_d.ex31datastore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //DataStore은 Kotlin의 Coroutine과 Flow를 사용하여 비동기적으로 데이터를 저정하고 읽어올 수 있다.
        //기존의 SharedPreference의 단점은
        //1. get 했을때 동기로 작용하며, Main Thread를 blocking한다...
        //2. apply했을때 비동기로 작용하나 완료에 대해서 수신 할 수 없다.
        //3. commit했을때 동기로 작용하며, Main Thread를 blocking한다... -> UI Thread인 MainThread를 blocking 한다는 것은 성능저하의 원인으로 작용 할 수 있다.....

        //이에 반해 DataStore은 Coroutine을 통해서 MainThread를 차단하지 않는 선에서 비동기 프로그램 방식을 제공하고
        //Exeption을 덤저주면서 새로운 스트림을 만들 수 있는 여지를 제공한다.

        //dataStore에는 2가지 종류가 있다.
        //1. Preference DataStore
        // SharedPreference처러 key를 이용하여 데이터를 저장 하고 접근...
        // 스키마를 정의하거나 타입을 보장 받을 수 없다..

        //2. Proto DataStore
        //객체형태로 값을 저장 할 수 있다.
        //스키마를 정의하거나 타입을 보장 할 수 있다.

        //객체 형태로 값을 저장한다면 Room으로 값을 저장하는것도 하나의 방법이기는 하다... 그냥 객체형태로 값을 저장해야 되는 데이터 라면 DataStore보다는 Room을 고려하는게 좋을거 같다..
        //일다는 Preference DataStore을 공부해 보자...

        with(binding) {

            btnWhite.setOnClickListener {

            }

            btnBlack.setOnClickListener {

            }
        }


    }
}