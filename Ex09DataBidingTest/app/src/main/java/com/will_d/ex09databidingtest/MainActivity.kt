package com.will_d.ex09databidingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.will_d.ex09databidingtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel : MainViewModel
    private lateinit var itemAdapter : ItemAdapter
    val isReadyToStart : ObservableBoolean by lazy { ObservableBoolean() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.

        var recyclerView : RecyclerView = binding.viewItems
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        itemAdapter = ItemAdapter()
        recyclerView.adapter = itemAdapter

    }
}