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

class ItalyFragment : Fragment() {
    lateinit var myView : View
    val btn1 : Button by lazy { myView.findViewById(R.id.to_london_from_italy) }
    val btn2 : Button by lazy { myView.findViewById(R.id.to_paris_from_italy)}


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("FragmentLifeCyclesTest", "ItalyFragment - onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FragmentLifeCyclesTest", "ItalyFragment - onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("FragmentLifeCyclesTest", "ItalyFragment - onCreateView")
        return inflater.inflate(R.layout.fragment_italy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("FragmentLifeCyclesTest", "ItalyFragment - onViewCreated")
        this.myView = view

        btn1.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_global_londonFragment)
        }

        btn2.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_global_parisFragment)
        }

    }

    //활성화
    override fun onStart() {
        super.onStart()
        Log.i("FragmentLifeCyclesTest", "ItalyFragment - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("FragmentLifeCyclesTest", "ItalyFragment - onResume")
    }

    //비활성화
    override fun onPause() {
        super.onPause()
        Log.i("FragmentLifeCyclesTest", "ItalyFragment - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("FragmentLifeCyclesTest", "ItalyFragment - onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("FragmentLifeCyclesTest", "ItalyFragment - onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("FragmentLifeCyclesTest", "ItalyFragment - onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("FragmentLifeCyclesTest", "ItalyFragment - onDetach")
    }

}