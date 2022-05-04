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

class ParisFragment : Fragment() {
    lateinit var myView : View
    val btn1 : Button by lazy { myView.findViewById(R.id.to_italy_from_paris) }
    val btn2 : Button by lazy { myView.findViewById(R.id.to_london_from_paris)}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("FragmentLifeCyclesTest", "ParisFragment - onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FragmentLifeCyclesTest", "ParisFragment - onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("FragmentLifeCyclesTest", "ParisFragment - onCreateView")
        return inflater.inflate(R.layout.fragment_paris, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("FragmentLifeCyclesTest", "ParisFragment - onViewCreated")
        this.myView = view

        btn1.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_global_italyFragment)
        }

        btn2.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_global_londonFragment)
        }
    }

    //활성화
    override fun onStart() {
        super.onStart()
        Log.i("FragmentLifeCyclesTest", "ParisFragment - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("FragmentLifeCyclesTest", "ParisFragment - onResume")
    }

    //비활성화
    override fun onPause() {
        super.onPause()
        Log.i("FragmentLifeCyclesTest", "ParisFragment - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("FragmentLifeCyclesTest", "ParisFragment - onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("FragmentLifeCyclesTest", "ParisFragment - onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("FragmentLifeCyclesTest", "ParisFragment - onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("FragmentLifeCyclesTest", "ParisFragment - onDetach")
    }
}