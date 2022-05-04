package com.will_d.ex17recyclerviewlistadapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MyItemTouchHelperCallback(private val recyclerView: RecyclerView)
    : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT
    ){
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        (recyclerView.adapter as MyRecyclerViewAdapter).moveItem(
            viewHolder.adapterPosition,
            target.adapterPosition //추적하면서 position을 확인하는게 adapterPosition
        )
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        (recyclerView.adapter as MyRecyclerViewAdapter).removeItem(viewHolder.layoutPosition) // 선택이된 Position을 반환
    }


    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when(actionState){
            ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.ACTION_STATE_SWIPE -> {
                (viewHolder as MyViewHolder).setAlpah(0.5f)
            }
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        (viewHolder as MyViewHolder).setAlpah(1.0f)
    }
}