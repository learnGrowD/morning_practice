package com.will_d.ex11bnvnavigationtest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment3 : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("FragmentLifeCycles", "Fragment#3 - onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FragmentLifeCycles", "Fragment#3 - onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("FragmentLifeCycles", "Fragment#3 - onCreateView")
        return inflater.inflate(R.layout.fragment_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("FragmentLifeCycles", "Fragment#3 - onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i("FragmentLifeCycles", "Fragment#3 - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("FragmentLifeCycles", "Fragment#3 - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("FragmentLifeCycles", "Fragment#3 - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("FragmentLifeCycles", "Fragment#3 - onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("FragmentLifeCycles", "Fragment#3 - onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("FragmentLifeCycles", "Fragment#3 - onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("FragmentLifeCycles", "Fragment#3 - onDetach")
    }
}