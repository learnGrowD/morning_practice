package com.will_d.ex20fragmentcomunication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.will_d.ex20fragmentcomunication.databinding.FragmentABinding
import com.will_d.ex20fragmentcomunication.databinding.FragmentBBinding

class BFragment : Fragment() {

    lateinit var binding : FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_b, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn.setOnClickListener {
            val result = Bundle()
            result.putString("DO", "Hello..")
            setFragmentResult("FragToActivity", result)
        }

    }


}