package com.will_d.ex12bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.BinderThread
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import javax.microedition.khronos.opengles.GL

object TestBindingAdapter {

    @BindingAdapter("app:text_lulu_lala")
    @JvmStatic
    fun setText(view : TextView, text : String){
        view.text = text + "\n" + text
    }

    @BindingAdapter("app:loadImg")
    @JvmStatic
    fun loadImage(imgView : ImageView, imgUrl : String){
            Glide.with(imgView.context)
                .load(imgUrl)
                .circleCrop()
                .into(imgView)
    }
}