package com.will_d.ex10navigationtest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class LondonFragment : Fragment() {
    lateinit var myView : View
    val btn1 : Button by lazy { myView.findViewById(R.id.to_paris_from_london) }
    val btn2 : Button by lazy { myView.findViewById(R.id.to_italy_from_london)}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("FragmentLifeCyclesTest", "LondonFragment - onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FragmentLifeCyclesTest", "LondonFragment - onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("FragmentLifeCyclesTest", "LondonFragment - onCreateView")
        return inflater.inflate(R.layout.fragment_london, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("FragmentLifeCyclesTest", "LondonFragment - onViewCreated")
        this.myView = view

        btn1.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_global_parisFragment)
        }

        btn2.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_global_italyFragment)
        }

    }

    //활성화
    override fun onStart() {
        super.onStart()
        Log.i("FragmentLifeCyclesTest", "LondonFragment - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("FragmentLifeCyclesTest", "LondonFragment - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("FragmentLifeCyclesTest", "LondonFragment - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("FragmentLifeCyclesTest", "LondonFragment - onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("FragmentLifeCyclesTest", "LondonFragment - onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("FragmentLifeCyclesTest", "LondonFragment - onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("FragmentLifeCyclesTest", "LondonFragment - onDetach")
    }
}