package com.omdeep.mykotlinroomdbapp.view

import android.app.Dialog
import android.os.Bundle
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omdeep.mykotlinroomdbapp.R
import com.omdeep.mykotlinroomdbapp.adapter.MyRecyclerViewAdapter
import com.omdeep.mykotlinroomdbapp.databinding.ActivityMainBinding
import com.omdeep.mykotlinroomdbapp.databinding.InsertLayoutBinding
import com.omdeep.mykotlinroomdbapp.db.User
import com.omdeep.mykotlinroomdbapp.db.UserDatabase
import com.omdeep.mykotlinroomdbapp.factory.UserDetailsViewModelFactory
import com.omdeep.mykotlinroomdbapp.repository.UserRepository
import com.omdeep.mykotlinroomdbapp.viewModel.MyViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: MyViewModel
    lateinit var user : User


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

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.profile_context_menu, menu)
    }

   override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item!!.itemId) {
            R.id.update_user -> {
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

                myViewModel.updateOrDeleteClick(user)
                return true
            }
            R.id.delete_user -> {

                return true
            }
//                R.id.call -> {
//                    Toast.makeText(applicationContext, "call code", Toast.LENGTH_LONG).show()
//                    return true
//                }
//                R.id.sms -> {
//                    Toast.makeText(applicationContext, "sms code", Toast.LENGTH_LONG).show()
//                    return true
//                }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displayUsersList() {
        myViewModel.usersList.observe(this, Observer {
            binding.recyclerView.adapter =MyRecyclerViewAdapter(it) { selectedItem: User ->
                onItemClickListener(
                    selectedItem
                )
            }
        })
    }

    private fun onItemClickListener(user : User) {
        Toast.makeText(this, "Selected name is ${user.firstName+" "+user.lName}", Toast.LENGTH_SHORT).show()
        myViewModel.updateOrDeleteClick(user)
    }
}