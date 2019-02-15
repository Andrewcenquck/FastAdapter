package com.mikepenz.fastadapter

import android.support.v7.widget.RecyclerView

/**
 * Created by flisar on 21.09.2016.
 */

interface ISelectionListener<Item : IItem<out RecyclerView.ViewHolder>> {
    /**
     * is called, whenever the provided item is selected or deselected
     *
     * @param item the item who's selection state changed
     * param selected the new selection state of the item
     */
    fun onSelectionChanged(item: Item?, selected: Boolean)
}
