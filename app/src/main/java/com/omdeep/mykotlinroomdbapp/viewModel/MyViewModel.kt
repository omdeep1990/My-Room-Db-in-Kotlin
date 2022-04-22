package com.omdeep.mykotlinroomdbapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omdeep.mykotlinroomdbapp.db.User
import com.omdeep.mykotlinroomdbapp.repository.UserRepository
import kotlinx.coroutines.launch

class MyViewModel(private val userRepository: UserRepository) : ViewModel() {

    var firstName = MutableLiveData<String?>()
    var lastName = MutableLiveData<String?>()
    var mobileNo = MutableLiveData<String?>()

    var saveOrUpdate = MutableLiveData<String>()
    var clearAllOrDelete = MutableLiveData<String>()

    var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete : User

//    private val message = MutableLiveData<Event<String>>()

    //TODO: Initializing values to any button or text view at first time
    init {
        //TODO: Setting value to Mutable Live Data
        saveOrUpdate.value = "SAVE"
        clearAllOrDelete.value = "CLEAR ALL"
    }

    fun saveOrUpdateClick() {
        //TODO: Here first 'firstName' is the column name and second 'firstName is the Mutable Live Data
        if (isUpdateOrDelete) {
            userToUpdateOrDelete.firstName = firstName.value!!
            userToUpdateOrDelete.lName = lastName.value!!
            userToUpdateOrDelete.mobileNo = mobileNo.value!!

            update(userToUpdateOrDelete)

            setNullValue()

//            saveOrUpdate.value = "Save"
//            clearAllOrDelete.value = "Clear All"
        } else {
            val fName = firstName.value!!
            val lName = lastName.value!!
            val mobNo = mobileNo.value!!
            insert(User(0, fName, lName, mobNo))
            setNullValue()
        }
    }

    fun updateOrDeleteClick(user: User) {
        //TODO: Here first 'firstName' is the Mutable live data and second 'firstName' is the column
        firstName.value = user.firstName
        lastName.value = user.lName
        mobileNo.value = user.mobileNo

        isUpdateOrDelete = true
        userToUpdateOrDelete = user

//        saveOrUpdate.value = "Upda"
    }

    fun insert(user: User) {
        viewModelScope.launch {
            userRepository.insert(user)

        }
    }

    fun clearAll() {
        viewModelScope.launch {
            userRepository.deleteAll()

        }
    }

    fun update(user: User) {
        viewModelScope.launch {
            userRepository.update(user)

        }
    }

    fun delete(user: User) {
        viewModelScope.launch {
            userRepository.delete(user)

        }
    }

    val usersList = userRepository.users

    fun setNullValue() {
        firstName.value = null
        lastName.value = null
        mobileNo.value = null
    }
}