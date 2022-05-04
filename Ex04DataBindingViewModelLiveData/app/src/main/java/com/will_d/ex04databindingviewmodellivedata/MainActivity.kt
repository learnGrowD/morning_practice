package com.will_d.ex04databindingviewmodellivedata

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.will_d.ex04databindingviewmodellivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var model : ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        model = ViewModelProvider(this)[ViewModel::class.java]
        binding.viewModel = model


        model.counter.observe(this) {
            binding.tv.text = ""+ it
        }

    }
}