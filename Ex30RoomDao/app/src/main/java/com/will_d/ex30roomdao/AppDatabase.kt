package com.will_d.ex30roomdao

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    private val mIsDatabaseCreated = MutableLiveData<Boolean>()

    companion object {
        @Volatile
        private var singleton : AppDatabase? = null
        private const val DATABASE_NAME = "app_database"
        @JvmStatic
        fun getInstance(context: Context, scope : CoroutineScope): AppDatabase {
            return singleton ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                singleton = instance
                instance
            }
        }
    }

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreate()
        }
    }

    private fun setDatabaseCreate() {
        mIsDatabaseCreated.postValue(true)
    }

    open fun getDatabaseCreated() : LiveData<Boolean> {
        return mIsDatabaseCreated
    }

    private class AppDatabaseCallback(private val scope : CoroutineScope) : RoomDatabase.Callback() {


        //데이터 베이스가 처음 생성되었을때 할 행동을 코딩 할 수 있음...
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            singleton?.let { databse ->
            scope.launch {
                populateDatabase(databse.userDao())
            }

            }
        }
        //데이터 베이스가 열릴때마다 할 활동을 만들 수 있음
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//        }

        suspend fun  populateDatabase(userDao: UserDao) {

            userDao.insert(UserEntity("Lilly", "여", "1993-07-25"))
        }
    }
}