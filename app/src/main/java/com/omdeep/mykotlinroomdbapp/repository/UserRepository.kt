package com.omdeep.mykotlinroomdbapp.repository

import com.omdeep.mykotlinroomdbapp.db.User
import com.omdeep.mykotlinroomdbapp.db.UserDao

class UserRepository(val dao: UserDao) {

    //TODO: Insert function called
    suspend fun insert(user : User) {
        dao.insertUser(user)
    }

    //TODO: Update function called
    suspend fun update(user: User) {
        dao.updateUser(user)
    }

    //TODO: Delete function called
    suspend fun delete(user: User) {
        dao.deleteUser(user)
    }

    //TODO: Delete all function called
    suspend fun deleteAll() {
        dao.deleteAllUSers()
    }

    //TODO: Fetching function called
    val users = dao.getUsers()
}