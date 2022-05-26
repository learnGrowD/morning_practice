package com.will_d.adaptertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.will_d.adaptertest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding : ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    val items = arrayListOf("qwfqwfqwf", "qwfqwfqwf", "qwfhmyjudfv", "thnfvsdcdwewf")
    val adapter : Adapter by lazy { Adapter(items, 0) }

    var a = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recycler.adapter = adapter


        binding.btn.setOnClickListener {
            a = !a
            if (a) {
                adapter.changeViewType(0)
            }else {
                adapter.changeViewType(1)
            }
        }

    }
}