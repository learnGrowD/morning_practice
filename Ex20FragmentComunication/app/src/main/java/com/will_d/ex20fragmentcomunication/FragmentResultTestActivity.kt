package com.will_d.ex20fragmentcomunication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentResultListener

class FragmentResultTestActivity : AppCompatActivity() {

    var bFrag : BFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_result_test)

        showFragment()

        supportFragmentManager.setFragmentResultListener("FragToActivity", this, object : FragmentResultListener {
            override fun onFragmentResult(requestKey: String, result: Bundle) {
                findViewById<TextView>(R.id.tv).text = result["DO"] as String

            }

        })



    }

    fun showFragment(){
        val tran = supportFragmentManager.beginTransaction()
        bFrag?.let {
            tran.show(it)
        } ?: BFragment().let {
            bFrag = BFragment()
            tran.add(R.id.con, it)
        }
        tran.commit()
    }

    fun showFragment2(){

    }



}