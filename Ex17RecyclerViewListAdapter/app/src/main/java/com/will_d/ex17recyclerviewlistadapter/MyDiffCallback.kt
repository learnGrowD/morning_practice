package com.will_d.ex17recyclerviewlistadapter

import androidx.recyclerview.widget.DiffUtil

class MyDiffCallback : DiffUtil.ItemCallback<Moster>(){
    override fun areItemsTheSame(oldItem: Moster, newItem: Moster): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: Moster, newItem: Moster): Boolean {
        return oldItem == newItem
    }
}