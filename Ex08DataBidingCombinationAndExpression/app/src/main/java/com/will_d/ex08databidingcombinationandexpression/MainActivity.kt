package com.will_d.ex08databidingcombinationandexpression

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.will_d.ex08databidingcombinationandexpression.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var model : Model
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        model = Model("Sam", "DO")
        binding.model = model


        binding.str = "Sally" // 객체 주소를 넘겨줌으로서 참조하게 만듬.


    }
}