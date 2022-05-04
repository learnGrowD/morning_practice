package com.will_d.ex13staticandsingleton

class SingletonTest1 private constructor(){
    companion object {
        @Volatile
        private var singleton : SingletonTest1? = null

        @JvmStatic
        fun getInstance() : SingletonTest1?{
            return singleton ?: synchronized(this){
                singleton ?: SingletonTest1().apply {
                    singleton = this
                }
            }
        }


    }
}