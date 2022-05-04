package com.will_d.ex10navigationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FragmentLifeCyclesTest", "MainActivity - onCreate")
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onStart() {
        super.onStart()
        Log.i("FragmentLifeCyclesTest", "MainActivity - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("FragmentLifeCyclesTest", "MainActivity - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("FragmentLifeCyclesTest", "MainActivity - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("FragmentLifeCyclesTest", "MainActivity - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("FragmentLifeCyclesTest", "MainActivity - onDestroy")
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}