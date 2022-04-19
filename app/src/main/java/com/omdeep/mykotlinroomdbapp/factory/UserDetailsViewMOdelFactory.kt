package com.omdeep.mykotlinroomdbapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omdeep.mykotlinroomdbapp.repository.UserRepository
import com.omdeep.mykotlinroomdbapp.viewModel.MyViewModel
import java.lang.IllegalArgumentException

class UserDetailsViewModelFactory(val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(userRepository) as T
        }
        throw IllegalArgumentException("ViewModel Not Found")
    }
}