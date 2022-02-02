package com.wagmattei.dynaquiz.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.wagmattei.dynaquiz.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application)   {

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllDate
    }

    fun addUser(user: User) {
        // Corroutines, rodar em um micro processo em backgroud
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}