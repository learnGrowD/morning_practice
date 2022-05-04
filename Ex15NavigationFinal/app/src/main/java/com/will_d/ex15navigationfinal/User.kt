package com.will_d.ex15navigationfinal

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name : String,
    var age : Int
) : Parcelable
