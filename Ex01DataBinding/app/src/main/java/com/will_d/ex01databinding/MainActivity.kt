package com.will_d.ex01databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import com.will_d.ex01databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //기존의 방식은 View 객체를 직접제어하여 화면 갱신
    //DataBinding -> View객체가 Data에 반응하여 스스로 화면 갱신
    //View에게 관찰할 데이터를 알려주고 데이터가 변경되면 View가 스스로 화면을 갱신

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = User("Sam", 20, false)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = user


    }
}