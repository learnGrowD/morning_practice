package com.will_d.ex12bindingadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.will_d.ex12bindingadapter.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    val binding : ActivitySecondBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_second) }
    val model : SecondVIewModel by lazy { SecondVIewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding.model = model

    }
}