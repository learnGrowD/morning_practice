package com.will_d.ex12bindingadapter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.will_d.ex12bindingadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding : ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    var introduce = "안녕하세여. 저는 콩진호 입니다."
    var imgUrl = "https://images.chosun.com/resizer/vSEQ-r5lmTYy6qc_BfHXZdJf4Gs=/616x0/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/75V44LBTMBCVPA5BGP7XH2N7IE.jpg"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.activity = this
        binding.lifecycleOwner = this

        binding.btn.setOnClickListener{
            startActivity(Intent(this, SecondActivity::class.java))
        }



    }
}