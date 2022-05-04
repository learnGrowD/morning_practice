package com.will_d.ex15navigationfinal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class DifDialogFragment : DialogFragment(){

    var navControll : NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialogfragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHost = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navControll = navHost.navController

        view.findViewById<TextView>(R.id.tv).setOnClickListener{
            val bl : Boolean? = navControll?.popBackStack()
            Log.i("qwfqwf", "" + bl)
        }


    }
}