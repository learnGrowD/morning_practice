package com.will_d.ex11bnvnavigationtest

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.will_d.ex11bnvnavigationtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FragmentLifeCycles", "MainActivity - onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController


    }

    override fun onBackPressed() {
        if (!navController.popBackStack()){
            super.onBackPressed()
        }else{
            AlertDialog.Builder(this)
                .setMessage("")
                .setPositiveButton("확인") { dialog, id ->
                    this@MainActivity.finish()
                }
                .setNegativeButton("취소") { dialog, id ->
                    dialog.dismiss()
                }.show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("FragmentLifeCycles", "MainActivity - onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("FragmentLifeCycles", "MainActivity - onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("FragmentLifeCycles", "MainActivity - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("FragmentLifeCycles", "MainActivity - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("FragmentLifeCycles", "MainActivity - onDestroy")
    }
}