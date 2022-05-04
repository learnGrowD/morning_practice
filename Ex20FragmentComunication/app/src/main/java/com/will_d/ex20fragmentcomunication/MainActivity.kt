package com.will_d.ex20fragmentcomunication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.will_d.ex20fragmentcomunication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding : ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    val fragments : Array<Fragment?> by lazy { arrayOfNulls(4) }

    var a = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //기존적으로 AFragment -> BFragment에게 Bundle로 데이터 전달 하는 방법...

        //ViewModel Data 공유
        //1. 프래그 먼트간 데어터 공유 --> Activity의 ViewModel을 매개로 하여...
        //2. 상위 프래그먼트와 하위 프래그먼트 간 데이터 공유

        //Fragment Result API --> like ActivityForResult...
        //1. 기본적으로 Fragment Result API를 통해서 데이터 전달
        //2. 상위 및 하위 프래그먼트 간 데이터 전달...

        binding.btn.setOnClickListener {
            startActivity(Intent(this, FragmentResultTestActivity::class.java))
        }

        binding.btn1.setOnClickListener {
            hideFragment(0)
            a++
            val bundle = Bundle()
            bundle.putString("Hello", "이거 진짜다 ${a}")
            showFragment(0, AFragment(), bundle)
        }

        binding.btn2.setOnClickListener {

        }

        binding.btn3.setOnClickListener {

        }

        binding.btn4.setOnClickListener {

        }

    }

    fun showFragment(what : Int, fragment : Fragment, bundle: Bundle?){
        val tran = supportFragmentManager.beginTransaction()
        fragments[what]?.let {
            bundle?.apply { it.arguments = this } //onHiddenChange에서 변경되는 BundleData 전송
            tran.show(it)
        }?: fragment.let {
            fragments[what] = it
            bundle?.apply { it.arguments = this } //onViewCreate에서 이 번들 데이터 받음...
            tran.add(R.id.con, fragment)
        }
        tran.commit()
    }

    fun hideFragment(what : Int){
        fragments[what]?.let {
            val tran = supportFragmentManager.beginTransaction()
            tran.hide(it)
            tran.commit()
        }

    }
}