package com.will_d.adaptertest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.will_d.adaptertest.databinding.RecyclerTypeABinding
import com.will_d.adaptertest.databinding.RecyclerTypeBBinding

class Adapter(
    val items : ArrayList<String>,
    var viewType: Int = 0
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun changeViewType(viewType : Int) {
        this.viewType = viewType
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> AVH(
                RecyclerTypeABinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            1 -> BVH(
                RecyclerTypeBBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> AVH(
                RecyclerTypeABinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = items[position]
        when(holder) {
            is AVH -> holder.bind(model)
            is BVH -> holder.bind(model)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return viewType
    }


    class AVH(val binding: RecyclerTypeABinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model : String) {
            binding.model = model
        }
    }

    class BVH(val binding: RecyclerTypeBBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model : String) {
            binding.model = model
        }


    }
}