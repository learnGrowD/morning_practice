package com.will_d.ex05databindingviewmodellivedata2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.will_d.ex05databindingviewmodellivedata2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var biding : ActivityMainBinding
    private lateinit var model  : MainVeiwModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        model = ViewModelProvider(this)[MainVeiwModel::class.java]
        biding.model = model


    }
}