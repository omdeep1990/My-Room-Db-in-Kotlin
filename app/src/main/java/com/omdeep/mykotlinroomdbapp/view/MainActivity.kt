package com.omdeep.mykotlinroomdbapp.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omdeep.mykotlinroomdbapp.R
import com.omdeep.mykotlinroomdbapp.databinding.ActivityMainBinding
import com.omdeep.mykotlinroomdbapp.databinding.InsertLayoutBinding
import com.omdeep.mykotlinroomdbapp.db.UserDatabase
import com.omdeep.mykotlinroomdbapp.factory.UserDetailsViewModelFactory
import com.omdeep.mykotlinroomdbapp.repository.UserRepository
import com.omdeep.mykotlinroomdbapp.viewModel.MyViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: MyViewModel
//    lateinit var binding1: InsertLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding1 = DataBindingUtil.setContentView(this, R.layout.insert_layout)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        //TODO: Initializing all the MVVM concepts used here
        val dao = UserDatabase.getInstance(this).dao
        val userRepository = UserRepository(dao)
        val factory = UserDetailsViewModelFactory(userRepository)
//        myViewModel = ViewModelProvider(this, factory).get[MyViewModel::class]
        myViewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]

//        //TODO: binding.userViewModel is the variable defined in its xml layout file
//        binding1.myViewModel = myViewModel
//        //TODO: Giving lifecycle owner to layout file
//        binding1.lifecycleOwner = this

        binding.floatingBtn.setOnClickListener {
            //TODO: New way of creating dialog box in Kotlin language
            var binding1: InsertLayoutBinding = InsertLayoutBinding.inflate(layoutInflater)
//            binding1 = DataBindingUtil.setContentView(this, R.layout.insert_layout)
            val dialog = Dialog(this)
            dialog.setContentView(binding1.root)
            dialog.show()
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }
    }
}