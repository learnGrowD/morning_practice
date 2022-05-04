package com.will_d.ex05databindingviewmodellivedata2

import android.media.Image
import android.widget.ImageView
import androidx.databinding.BindingAdapter

class Item(title : String, status : String, fakeLoadingTimeSeconds : Int) {

    var title : String = ""
    var status : String = ""
    set(value) {
        field = value
        listener.onStatusChanged(this, field)
    }
    var fakeLoadingTimeSeconds : Int = -1
    lateinit var listener: OnStatusChangeListener

    init {
        this.title = title
        this.status = status
        this.fakeLoadingTimeSeconds = fakeLoadingTimeSeconds
    }

    companion object {
        @BindingAdapter({"statusIcon"})
        @JvmStatic
        fun loadStatusIcon(imageView : ImageView, status : String){
            when(status){
                "DONE" -> imageView.setImageResource(com.google.android.material.R.drawable.ic_clock_black_24dp)
                "DOING" -> imageView.setImageResource(com.google.android.material.R.drawable.ic_keyboard_black_24dp)
                else -> imageView.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }

    fun load(){
        status = "DOING"

    }

    interface OnStatusChangeListener {
        fun onStatusChanged(item : Item, status : String)
    }



}