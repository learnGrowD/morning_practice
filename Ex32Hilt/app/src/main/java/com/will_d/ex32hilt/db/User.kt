package com.will_d.ex32hilt.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "gender")
    val gender : String,

    val birth : String,
)
{
//    @PrimaryKey(autoGenerate = true) val id = 0
}