package com.will_d.ex32hilt.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [User::class], version = 1, exportSchema = false)
//@TypeConverters 나중에 DB에 저장할때 TypeConverters를 정의해서 저장해야 될 상황이 있을것이다 이때 이거 사용해보기...
abstract class AppDatabase : RoomDatabase(){
    abstract fun UserDao() : UserDao
}