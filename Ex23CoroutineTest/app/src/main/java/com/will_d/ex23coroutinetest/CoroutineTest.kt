package com.will_d.ex23coroutinetest

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(){
    print("start Main Thread")

    GlobalScope.launch {
        delay(3000)
        print("in Coroutine")
    }

    print("End Main Thread")

}