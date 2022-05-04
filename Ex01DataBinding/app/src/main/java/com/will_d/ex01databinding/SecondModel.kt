package com.will_d.ex01databinding

import androidx.databinding.ObservableField

class SecondModel(
    title : String,
    subTitle : String,
    visible : Boolean
) {

    val title = ObservableField<String>()
    val subTitle = ObservableField<String>()
    val visible = ObservableField<Boolean>()

    init {
        this.title.set(title)
        this.subTitle.set(subTitle)
        this.visible.set(visible)
    }

    fun onButtonClick(){
        val temp = visible.get() ?: true
        visible.set(!temp)
    }



}