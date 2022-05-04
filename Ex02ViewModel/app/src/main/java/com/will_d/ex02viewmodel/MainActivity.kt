package com.will_d.ex02viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.will_d.ex02viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
//    private lateinit var userViewModel : UserViewModel //이거랑
    private val model : UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java) //이거를
        binding.user = model
//        setContentView(R.layout.activity_main)
        /*
        val nameObserver = Observer<Int> {
            binding.textviewHeight.text = it.toString()
        }


        userViewModel.height.observe(this, nameObserver)
         */


        model.height.observe(this) {
            binding.textviewHeight.text = it.toString()
        }


    }
}