package com.will_d.ex01databinding

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class User(name : String, age : Int, fav : Boolean) {
    val name : ObservableField<String> by lazy { ObservableField(name) }
    val age : ObservableInt by lazy { ObservableInt(age) }
    val fav : ObservableBoolean by lazy { ObservableBoolean(fav) }

    val msg : ObservableField<String> by lazy { ObservableField() }
    val msg2 : ObservableField<String> by lazy { ObservableField() }
    var data : String = ""

    var isNameChange = false;


    fun checkFav(buttonView : CompoundButton?, isChecked : Boolean){
        fav.set(isChecked)
    }

    fun changeName(view : View?){
        isNameChange = !isNameChange
        if (isNameChange) name.set("Lobin")
        else name.set("Kavin")
    }

    fun increaseAge(view : View?){
        age.set(age.get() + 1)
    }

    fun toggleFav(view : View?){
        fav.set(!fav.get())
    }

    fun changeText(s : CharSequence?, start : Int, before : Int, count : Int){
        msg.set(s.toString())
    }

    fun changeText2(s : CharSequence?, start : Int, before: Int, count: Int){
        data = s.toString()
    }

    fun inputComplete(view : View?){
        msg2.set(data)
    }


}