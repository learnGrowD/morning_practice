package com.will_d.ex32hilt.di

import android.content.Context
import androidx.room.Room
import com.will_d.ex32hilt.db.AppDatabase
import com.will_d.ex32hilt.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {


    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context : Context) : AppDatabase{
        return Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                "app_database.db"
            ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providUserDao(appDatabase: AppDatabase) : UserDao {
        return appDatabase.UserDao()
    }
}