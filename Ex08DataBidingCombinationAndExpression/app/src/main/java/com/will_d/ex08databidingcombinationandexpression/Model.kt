package com.will_d.ex08databidingcombinationandexpression

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class Model(val firstName : String, val lastName : String) {

    //참조형
    var re : Any? = null
    val a : String? = "Hello"
    val b : Int = 10
    var age : Int = 10
    var c : String? = "Good Android"



    //이벤트 처리
    //1. 메서드 참조
    //2. 리스너 결합

    val name : ObservableField<String> by lazy { ObservableField() }

    fun chnageName(view : View?){
        name.set("Sam")
    }


    val age2 : ObservableInt by lazy { ObservableInt() }
    fun increaseAge2(){
        age2.set(age2.get() + 1)
    }

    val name2 : ObservableField<String> by lazy { ObservableField() }

    fun changeName2(view : View?){
        name2.set("${view?.isVisible}")
    }

    fun onCompletedChanged(tv : TextView?, isChecked : Boolean){
        Log.i("vasdasd", "${tv?.text.toString()} Android ${isChecked}")
    }

    //자기자신 보내기 테스트
    fun changeText(btn : Button){
        Log.i("qwdqwfqwf", btn.text.toString())
    }

    fun parmsTest() : String{
        return "Hello Android"
    }

    fun onLongClickTest() : Boolean{
        Log.i("qwdqwf", "qwfqwf")
        return true
    }

    fun doSomething(view : View?){

    }

    fun doSomething2(view : View?){

    }



}