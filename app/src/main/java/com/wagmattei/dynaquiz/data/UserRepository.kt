package com.wagmattei.dynaquiz.data

import androidx.lifecycle.LiveData
import com.wagmattei.dynaquiz.data.dao.UserDao
import com.wagmattei.dynaquiz.data.model.User

class UserRepository (private val userDao: UserDao){
    val readAllDate: LiveData<List<User>> = userDao.realAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}