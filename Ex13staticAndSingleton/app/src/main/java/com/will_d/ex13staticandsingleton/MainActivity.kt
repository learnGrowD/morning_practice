package com.will_d.ex13staticandsingleton

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    //결론

    val btn : Button by lazy { findViewById(R.id.btn) }
    val btn2 : Button by lazy { findViewById(R.id.btn2) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a = SingletonTest2
        val b = SingletonTest1.getInstance()

        btn.setOnClickListener{
            var c = CompanionObjectMemoryTest.Companion
            var d = BB
            Log.i("testCode", "AA : " + System.identityHashCode(c))
            Log.i("testCode", "BB : " + System.identityHashCode(d))
        }

        btn2.setOnClickListener{
            startActivity(Intent(this, SecondActivity::class.java))
        }

        var ddd = CompanionObjectMemoryTest()


        //일단 정리
        //companion object, object는 같은것 --> singleton 객체 단, 파라미터 전달 불가
        //이 안에서 생기는 변수들은 일반 변수 단 이 객체를 참조하는 변수가 static해서 안에있는 변수들의 효용성이 static한것
        //static 변수를 만들고 싶다면 companion object, object에서 @JvmField, @JvmStatic, const keyword 사용...




    }
}