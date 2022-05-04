package com.will_d.ex13staticandsingleton

object BB {
    const val a = 1 //static
    const val b = "" //static
    @JvmField
    var k = "d" //static

    @JvmField
    val d = AA() //static

    @JvmStatic //static method...
    fun aaa(){

    }
}