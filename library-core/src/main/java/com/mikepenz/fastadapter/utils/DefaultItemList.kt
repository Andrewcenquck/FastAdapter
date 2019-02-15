package com.mikepenz.fastadapter.utils

import android.support.v7.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.IItemList

/**
 * The default item list
 */

abstract class DefaultItemList<Item : IItem<out RecyclerView.ViewHolder>> : IItemList<Item> {

    var fastAdapter: FastAdapter<Item>? = null
}
