package com.will_d.ex15navigationfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.will_d.ex15navigationfinal.databinding.FragmentABinding

class AFragment : Fragment(){

    private lateinit var binding : FragmentABinding


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
        binding.btn.setOnClickListener{
            val arr = IntArray(3)
            val arr2 = arrayOf<String>()
            val arr3 = arrayOf<String>()


            val navAction = AFragmentDirections.actionFragmentAToFragmentB(arr2, arr)
            it.findNavController().navigate(navAction)
        }


        binding.btn2.setOnClickListener{
            it.findNavController().navigate(R.id.action_fragment_a_to_dialogfragment_a)
        }
    }
}