package com.will_d.ex15navigationfinal

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

class DialogFragmentTest : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialogfragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn).setOnClickListener{
            val navHost = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
            navHost.navController.navigate(R.id.action_dialogfragment_a_to_dialogfragment_b)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("dialogTest", "onDestroy")
    }
}