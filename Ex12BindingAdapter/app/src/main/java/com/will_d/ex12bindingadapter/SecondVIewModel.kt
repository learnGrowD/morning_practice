package com.will_d.ex12bindingadapter

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class SecondVIewModel {
    val myText = ObservableField<String>()
    var index : Long = 0
    val memoList = ObservableArrayList<Memo>()


    fun addMemo(){
        memoList.add(Memo(index++, myText.get().toString()))
        myText.set("")
    }
}