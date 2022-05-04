package com.will_d.ex17recyclerviewlistadapter

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.will_d.ex17recyclerviewlistadapter.databinding.LayoutViewholderBinding

class MyViewHolder(private val binding : LayoutViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data : Moster){
        with(binding) {
            tvName.text = "Name : ${data.name}"
            tvRace.text = "Name : ${data.race}"
            tvLevel.text = "Name : ${data.level}"
            tvStats.text = "Name : ${data.stats[0]} / Mp: ${data.stats[1]} / EXP: ${data.stats[2]}"
            tvEncount.text = "Name : ${data.encount}"

            layoutViewholder.setOnClickListener{
                Snackbar.make(it, "Item $layoutPosition touched!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    fun setAlpah(alpha : Float){
        with(binding) {
            tvName.alpha = alpha
            tvRace.alpha = alpha
            tvLevel.alpha = alpha
            tvStats.alpha = alpha
            tvEncount.alpha = alpha
        }
    }
}