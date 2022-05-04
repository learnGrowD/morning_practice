package com.will_d.ex15navigationfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.will_d.ex15navigationfinal.databinding.FragmentBBinding

class BFragment : Fragment(){
    lateinit var binding : FragmentBBinding
    val arg : BFragmentArgs by navArgs<BFragmentArgs>()

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
        binding.tv.text = "" + arg.arr

        binding.btn.setOnClickListener{
            val user : User = User("DO", 25)
            val navAction = BFragmentDirections.actionFragmentBToFragmentC(user)
            it.findNavController().navigate(navAction)
        }
    }
}