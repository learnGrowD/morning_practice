package com.will_d.ex15navigationfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //android navigation 구성요소
        //1. navHost
        //2. navGraph
        //3. navController

        //fragment, dialog, activity 기본적인 고려사항
        //1. 데이터
        //2. 어디로 이동하는지
        //3. 딥링크

        //action 기본적인 고려사항
        //1. 애니메이션
        //2. 액션과 같이 갈 데이터 (default value)
        //3. pop behavior
        //4. Launch option...

        //BackPress관리는 어떻게??????

        //callBack 처리는 어떻게?????? -> Activity를 매개로 하면 될거 같은데, 아니면 의존성 주입을 통해서도 해결가능!!!


        //BackStack관리에대해서 공부
        //3. pop behavior
        //4. Launch option...
        //BackPress관리는 어떻게??????
        //popBackStack






        //navController 찾는 방법
        //Fragment에게 요청
        //Activity에게 요청
        //View에게 요청
        //이런식으로 navHost에게 요청
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
//        navHost.navController.navigate()
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val backStackEntryCount = navHostFragment.childFragmentManager.backStackEntryCount
        if (backStackEntryCount!=0){
            super.onBackPressed()
        }else {
            AlertDialog.Builder(this)
                .setTitle("HelloNavigation")
                .setPositiveButton("확인"){ dialog, id ->
                    finish()
                }
                .show()
        }

    }
}