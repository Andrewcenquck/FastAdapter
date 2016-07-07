package com.mikepenz.fastadapter.app.items;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.app.R;
import com.mikepenz.fastadapter.items.AbstractItem;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class RealmSampleUserItem extends RealmObject implements IItem<RealmSampleUserItem, RealmSampleUserItem.ViewHolder> {

    private String name;

    // Standard getters & setters generated by your IDE…
    public String getName() {
        return name;
    }

    /**
     * set the name of this item
     *
     * @param name
     * @return this
     */
    public RealmSampleUserItem withName(String name) {
        this.name = name;
        return this;
    }

    // the identifier for this item
    @PrimaryKey
    protected long mIdentifier = -1;

    /**
     * set the identifier of this item
     *
     * @param identifier
     * @return this
     */
    public RealmSampleUserItem withIdentifier(long identifier) {
        this.mIdentifier = identifier;
        return this;
    }

    /**
     * @return the identifier of this item
     */
    @Override
    public long getIdentifier() {
        return mIdentifier;
    }

    // the tag for this item
    @Ignore
    protected Object mTag;

    /**
     * set the tag of this item
     *
     * @param object
     * @return this
     */
    public RealmSampleUserItem withTag(Object object) {
        this.mTag = object;
        return this;
    }

    /**
     * @return the tag of this item
     */
    @Override
    public Object getTag() {
        return mTag;
    }

    // defines if this item is enabled
    @Ignore
    protected boolean mEnabled = true;

    /**
     * set if this item is enabled
     *
     * @param enabled true if this item is enabled
     * @return this
     */
    public RealmSampleUserItem withEnabled(boolean enabled) {
        this.mEnabled = enabled;
        return this;
    }

    /**
     * @return if this item is enabled
     */
    @Override
    public boolean isEnabled() {
        return mEnabled;
    }

    // defines if the item is selected
    @Ignore
    protected boolean mSelected = false;

    /**
     * set if this item is selected
     *
     * @param selected true if this item is selected
     * @return this
     */
    @Override
    public RealmSampleUserItem withSetSelected(boolean selected) {
        this.mSelected = selected;
        return this;
    }

    /**
     * @return if this item is selected
     */
    @Override
    public boolean isSelected() {
        return mSelected;
    }

    // defines if this item is selectable
    @Ignore
    protected boolean mSelectable = true;

    /**
     * set if this item is selectable
     *
     * @param selectable true if this item is selectable
     * @return this
     */
    @Override
    public RealmSampleUserItem withSelectable(boolean selectable) {
        this.mSelectable = selectable;
        return this;
    }

    /**
     * @return if this item is selectable
     */
    @Override
    public boolean isSelectable() {
        return mSelectable;
    }

    /**
     * returns the type of the Item. Can be a hardcoded INT, but preferred is a defined id
     *
     * @return
     */
    @Override
    public int getType() {
        return R.id.fastadapter_realm_sample_user_item_id;
    }

    /**
     * returns the layout for the given item
     *
     * @return
     */
    @Override
    public int getLayoutRes() {
        return R.layout.letter_item;
    }

    /**
     * generates a view by the defined LayoutRes
     *
     * @param ctx
     * @return
     */
    @Override
    public View generateView(Context ctx) {
        ViewHolder viewHolder = getViewHolder(LayoutInflater.from(ctx).inflate(getLayoutRes(), null, false));

        //as we already know the type of our ViewHolder cast it to our type
        bindView(viewHolder);

        //return the bound view
        return viewHolder.itemView;
    }

    /**
     * generates a view by the defined LayoutRes and pass the LayoutParams from the parent
     *
     * @param ctx
     * @param parent
     * @return
     */
    @Override
    public View generateView(Context ctx, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(LayoutInflater.from(ctx).inflate(getLayoutRes(), parent, false));

        //as we already know the type of our ViewHolder cast it to our type
        bindView(viewHolder);
        //return the bound and generatedView
        return viewHolder.itemView;
    }

    /**
     * Generates a ViewHolder from this Item with the given parent
     *
     * @param parent
     * @return
     */
    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        return getViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(), parent, false));
    }

    /**
     * This method returns the ViewHolder for our item, using the provided View.
     * By default it will try to get the ViewHolder from the ViewHolderFactory. If this one is not implemented it will go over the generic way, wasting ~5ms
     *
     * @param v
     * @return the ViewHolder for this Item
     */
    private ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    /**
     * Binds the data of this item to the given holder
     *
     * @param holder
     */
    @Override
    public void bindView(ViewHolder holder) {
        //set the selected state of this item. force this otherwise it may is missed when implementing an item
        holder.itemView.setSelected(isSelected());
        //set the tag of this item to this object (can be used when retrieving the view)
        holder.itemView.setTag(this);

        //set the name
        holder.name.setText(name);
    }

    /**
     * If this item equals to the given identifier
     *
     * @param id identifier
     * @return true if identifier equals id, false otherwise
     */
    @Override
    public boolean equals(int id) {
        return id == mIdentifier;
    }

    /**
     * If this item equals to the given object
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractItem<?, ?> that = (AbstractItem<?, ?>) o;
        return mIdentifier == that.getIdentifier();
    }

    /**
     * the hashCode implementation
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Long.valueOf(mIdentifier).hashCode();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;

        public ViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.text);
        }
    }
}