package com.will_d.ex12bindingadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.will_d.ex12bindingadapter.databinding.RecyclerMemoBinding

class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.VH>() {

    var items : ArrayList<Memo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = RecyclerMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
     return items.size
    }

    class VH(binding:RecyclerMemoBinding) : RecyclerView.ViewHolder(binding.root){
        val binding = binding
        fun bind(memo:Memo){
            binding.model = memo
        }

    }
}