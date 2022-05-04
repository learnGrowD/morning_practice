package com.will_d.ex20fragmentcomunication

import android.hardware.lights.Light
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.will_d.ex20fragmentcomunication.databinding.FragmentABinding

class AFragment : Fragment() {

    lateinit var binding : FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_a, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            binding.tv.text = it["Hello"] as String
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        arguments?.let {
            binding.tv.text = it["Hello"] as String
        }
    }


}