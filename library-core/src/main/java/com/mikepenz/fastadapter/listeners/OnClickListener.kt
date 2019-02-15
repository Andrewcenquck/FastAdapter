package com.mikepenz.fastadapter.listeners

import android.support.v7.widget.RecyclerView
import android.view.View
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.IItem

interface OnClickListener<Item : IItem<out RecyclerView.ViewHolder>> {
    /**
     * the onClick event of a specific item inside the RecyclerView
     *
     * @param v        the view we clicked
     * @param adapter  the adapter which is responsible for the given item
     * @param item     the IItem which was clicked
     * @param position the global position
     * @return true if the event was consumed, otherwise false
     */
    fun onClick(v: View?, adapter: IAdapter<Item>, item: Item, position: Int): Boolean
}
