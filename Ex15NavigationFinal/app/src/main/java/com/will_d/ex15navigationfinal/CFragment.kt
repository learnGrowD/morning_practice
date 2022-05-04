package com.will_d.ex15navigationfinal

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.will_d.ex15navigationfinal.databinding.FragmentCBinding

class CFragment : Fragment(){
    lateinit var binding : FragmentCBinding
    val args : CFragmentArgs by navArgs<CFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_c, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tv.text = "" + args.user.name + " : " + args.user.age
        binding.btn.setOnClickListener{
            var amount = 10
            var bundle = bundleOf("amount" to amount)
            var bundel2 = Bundle()
            bundel2.putString("A", "android")
            bundel2.putInt("B", 10)
            it.findNavController().navigate(R.id.action_fragment_c_to_fragment_d, bundel2)
        }
    }
}