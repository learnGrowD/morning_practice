package com.will_d.ex30roomdao

import androidx.lifecycle.LiveData
import androidx.room.*

interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY name ASC") //ASC 오름차순... DESC 내림차순...
    fun getAlphabetizedUsers() : LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserEntity)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()


    //Query를 공부하면 다 알게될 내용들.... 결국 SQL문을 제대로 아는것이 가장 중요함 알면 그냥 철저하게 통제 할 수 있는거지
//    @Update
//    suspend fun update(user : UserEntity)
//
//    @Delete
//    suspend fun delete(user: UserEntity)
//
//    //이름으로 데이터 가져오기
//    @Query("SELECT * FROM user_table WHERE name = :name")
//    suspend fun loadUser(name : String) : UserEntity
//
//    //특정 필드 업데이트
//    @Query("UPDATE user_table SET name = :name WHERE id= :id")
//    suspend fun updateName(id : Int, name : String)

    //특정 단어가 포함된 데이터 가져오기
//    @Query("SELECT * FROM hamster WHERE name LIKE:search")
//    fun loadHamster(search : String?) : Flowable<List<Hamster>>




}