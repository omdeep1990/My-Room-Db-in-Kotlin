package com.omdeep.mykotlinroomdbapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.omdeep.mykotlinroomdbapp.databinding.InsertLayoutBinding
import com.omdeep.mykotlinroomdbapp.db.User

class MyRecyclerViewAdapter {

    class MyViewHolder(val binding : InsertLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, selectedItem: (User) -> Unit) {
//            binding.
        }
    }
}