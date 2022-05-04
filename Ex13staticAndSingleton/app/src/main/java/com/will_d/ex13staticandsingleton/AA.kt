package com.will_d.ex13staticandsingleton

class AA {
    companion object {
        const val a = 10 //static
        const val b = "" //static

        @JvmField
        var c = "" //static

        @JvmField
        var k = BB //static한 변수가 singleton 객체 참조...

        @JvmStatic  //static method..
        fun aaa(){

        }

    }
}