package com.will_d.ex06mvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class VPAdapter(val context : Context, val items : ArrayList<Fragment>) : RecyclerView.Adapter<VPAdapter.VH>() {
    init {
        items.add(FragSearch())
        items.add(FragBoard())
        items.add(FragMap())
        items.add(FragSearch())
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
       init {

       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView : View = LayoutInflater.from(context).inflate(R.layout.vp_frag_con, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val fragment : Fragment = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

}