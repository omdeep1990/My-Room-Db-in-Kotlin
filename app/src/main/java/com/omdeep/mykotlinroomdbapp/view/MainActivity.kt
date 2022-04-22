package com.omdeep.mykotlinroomdbapp.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        //TODO: Initializing all the MVVM concepts used here
        val dao = UserDatabase.getInstance(this).dao
        val userRepository = UserRepository(dao)
        val factory = UserDetailsViewModelFactory(userRepository)
        myViewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]


        displayUsersList()



        binding.floatingBtn.setOnClickListener {
            //TODO: New way of creating dialog box in Kotlin language
            val dialog = Dialog(this)
            val binding1: InsertLayoutBinding = DataBindingUtil.inflate(dialog.layoutInflater, R.layout.insert_layout, null, false)
            dialog.setContentView(binding1.root)
            dialog.show()
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            //TODO: Giving lifecycle owner to layout file
            binding1.myViewModel = myViewModel

            //TODO: Giving lifecycle owner to layout file
            binding1.lifecycleOwner = this

        }
    }

    private fun displayUsersList() {
        myViewModel.usersList.observe(this, Observer {
//            binding.recyclerView.adapter =
        })
    }
}