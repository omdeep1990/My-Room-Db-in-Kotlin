package com.omdeep.mykotlinroomdbapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.omdeep.mykotlinroomdbapp.databinding.DisplayLayoutBinding
import com.omdeep.mykotlinroomdbapp.db.User

class MyRecyclerViewAdapter(private val userList: List<User>, var selectedItem: (User) -> Unit) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {



    class MyViewHolder(val binding : DisplayLayoutBinding) : RecyclerView.ViewHolder(binding.root), AdapterView.OnItemLongClickListener {
        fun bind(user: User, selectedItem: (User) -> Unit) {
            binding.yourName.text = user.firstName+" "+user.lName
            binding.mobileNumber.text = user.mobileNo
            binding.rootItem.setOnClickListener {
                selectedItem(user)
            }

        }


        override fun onItemLongClick(parent: AdapterView<*>?, view: View, position: Int, id: Long): Boolean {
//            fun bind(user: User) {
//                user = userList[position]
//            }

            return false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DisplayLayoutBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position], selectedItem)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}