package com.will_d.ex09databidingtest

class Item (val listener : OnStatusChangeListener, val title : String, val status : String, val fakeLoadingTimeSeconds : Int){
    interface OnStatusChangeListener{
        fun onStatusChanged(item : Item, status : String)
    }
}