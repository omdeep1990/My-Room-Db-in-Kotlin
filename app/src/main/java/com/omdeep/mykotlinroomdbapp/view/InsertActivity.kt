package com.omdeep.mykotlinroomdbapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omdeep.mykotlinroomdbapp.R
import com.omdeep.mykotlinroomdbapp.databinding.ActivityInsertBinding
import com.omdeep.mykotlinroomdbapp.db.UserDatabase
import com.omdeep.mykotlinroomdbapp.factory.UserDetailsViewModelFactory
import com.omdeep.mykotlinroomdbapp.repository.UserRepository
import com.omdeep.mykotlinroomdbapp.viewModel.MyViewModel

class InsertActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInsertBinding
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_insert)
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        //TODO: Initializing all the MVVM concepts used here
        val dao = UserDatabase.getInstance(this).dao
        val userRepository = UserRepository(dao)
        val factory = UserDetailsViewModelFactory(userRepository)
        myViewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]
//        myViewModel = ViewModelProvider(this, factory).get[MyViewModel::class.java]
//        myViewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]

        //TODO: binding.userViewModel is the variable defined in its xml layout file
        binding.myViewModel1 = myViewModel
        //TODO: Giving lifecycle owner to layout file
        binding.lifecycleOwner = this
    }
}