package com.will_d.ex12bindingadapter

import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Modifier

object DataBindingUtils {
    @BindingAdapter("bind_memolist")
    @JvmStatic
    fun bindMemoList(recyclerView: RecyclerView, items : ObservableArrayList<Memo>){
        if (recyclerView.adapter == null){
            val lm = LinearLayoutManager(recyclerView.context)
            val adapter = RecyclerAdapter()
            recyclerView.layoutManager = lm
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as RecyclerAdapter).items = items
        recyclerView.adapter?.notifyDataSetChanged()

        Log.i("qwdqwqwf", "qwfqwfqwf")
    }

    @BindingAdapter("bind_text")
    @JvmStatic
    fun bindText(textView: TextView, id:Long){
        textView.text = "" + id
    }
}