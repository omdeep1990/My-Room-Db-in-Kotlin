package com.omdeep.mykotlinroomdbapp.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {

    //TODO: All CRUD operations used here
    @Insert
    suspend fun insertUser(user: User) : Long

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table")
    fun getUsers() : LiveData<List<User>>

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUSers()

}