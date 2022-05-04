package com.will_d.ex06mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.will_d.ex06mvvm.databinding.FragSearchBinding

class FragSearch : Fragment() {

    private lateinit var vm : CoronaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragSearchBinding = DataBindingUtil.inflate(inflater, R.layout.frag_search, container, false)
        vm = ViewModelProvider(this)[CoronaViewModel::class.java]


    }

}