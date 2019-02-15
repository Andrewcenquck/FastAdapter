package com.mikepenz.fastadapter.app.items.expandable

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.IClickable
import com.mikepenz.fastadapter.ISubItem
import com.mikepenz.fastadapter.app.R
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem
import com.mikepenz.fastadapter.listeners.OnClickListener

/**
 * Created by mikepenz on 28.12.15.
 */
open class SimpleSubExpandableItem : AbstractExpandableItem<SimpleSubExpandableItem.ViewHolder>(), IClickable<SimpleSubExpandableItem>, ISubItem<SimpleSubExpandableItem.ViewHolder> {

    var header: String? = null
    var name: String? = null
    var description: String? = null

    private var mOnClickListener: OnClickListener<SimpleSubExpandableItem>? = null

    //we define a clickListener in here so we can directly animate
    /**
     * we overwrite the item specific click listener so we can automatically animate within the item
     *
     * @return
     */
    override var onItemClickListener: OnClickListener<SimpleSubExpandableItem>? = object : OnClickListener<SimpleSubExpandableItem> {
        override fun onClick(v: View?, adapter: IAdapter<SimpleSubExpandableItem>, item: SimpleSubExpandableItem, position: Int): Boolean {
            if (item.subItems != null) {
                v?.findViewById<View>(R.id.material_drawer_icon)?.let {
                    if (!item.isExpanded) {
                        ViewCompat.animate(it).rotation(180f).start()
                    } else {
                        ViewCompat.animate(it).rotation(0f).start()
                    }
                }
                return mOnClickListener?.onClick(v, adapter, item, position) ?: true
            }
            return mOnClickListener?.onClick(v, adapter, item, position) ?: true
        }
    }
        set(onClickListener) {
            this.mOnClickListener = onClickListener // on purpose
        }

    override var onPreItemClickListener: OnClickListener<SimpleSubExpandableItem>?
        get() = null
        set(onClickListener) {

        }

    override//this might not be true for your application
    var isSelectable: Boolean
        get() = subItems == null
        set(value: Boolean) {
            super.isSelectable = value
        }

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    override val type: Int
        get() = R.id.fastadapter_expandable_item_id

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    override val layoutRes: Int
        get() = R.layout.expandable_item

    fun withHeader(header: String): SimpleSubExpandableItem {
        this.header = header
        return this
    }

    fun withName(name: String): SimpleSubExpandableItem {
        this.name = name
        return this
    }

    fun withDescription(description: String): SimpleSubExpandableItem {
        this.description = description
        return this
    }

    /**
     * binds the data of this item onto the viewHolder
     *
     * @param holder the viewHolder of this item
     */
    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        //get the context
        val ctx = holder.itemView.context

        //set the background for the item
        holder.view.clearAnimation()
        //ViewCompat.setBackground(holder.view, FastAdapterUIUtils.getSelectableBackground(ctx, Color.RED, true))
        //set the text for the name
        holder.name.text = name
        //set the text for the description or hide
        holder.description.text = description

        if (subItems == null || subItems?.size == 0) {
            holder.icon?.visibility = View.GONE
        } else {
            holder.icon?.visibility = View.VISIBLE
        }

        if (isExpanded) {
            holder.icon?.rotation = 0f
        } else {
            holder.icon?.rotation = 180f
        }
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)
        holder.name?.text = null
        holder.description?.text = null
        //make sure all animations are stopped
        holder.icon?.clearAnimation()
    }

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    /**
     * our ViewHolder
     */
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.material_drawer_name)
        var description: TextView = view.findViewById(R.id.material_drawer_description)
        var icon: ImageView = view.findViewById(R.id.material_drawer_icon)
    }
}
