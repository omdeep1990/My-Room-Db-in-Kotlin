package com.omdeep.mykotlinroomdbapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omdeep.mykotlinroomdbapp.repository.UserRepository

class MyViewModel(private val userRepository: UserRepository) : ViewModel() {

    var firstName = MutableLiveData<String?>()
    var lastName = MutableLiveData<String?>()
    var mobileNo = MutableLiveData<String?>()

    var saveOrUpdate = MutableLiveData<String>()
    var clearAllOrDelete = MutableLiveData<String>()

    //TODO: Initializing values to any button or text view at first time
    init {
        //TODO: Setting value to Mutable Live Data
        saveOrUpdate.value = "SAVE"
        clearAllOrDelete.value = "CLEAR ALL"
    }
}