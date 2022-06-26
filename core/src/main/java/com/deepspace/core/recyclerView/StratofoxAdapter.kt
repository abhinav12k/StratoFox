package com.deepspace.core.recyclerView

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class StratofoxAdapter(private val viewBinders: Map<BaseItemClass, BaseItemViewBinder>) :
    ListAdapter<Any, ViewHolder>(BaseDiffCallback(viewBinders)) {

    private val viewTypeToBinders = viewBinders.mapKeys { it.value.getItemViewType() }

    private fun getViewBinder(viewType: Int): BaseItemViewBinder =
        viewTypeToBinders.getValue(viewType)

    override fun getItemViewType(position: Int): Int {
        return viewBinders.getValue(super.getItem(position).javaClass).getItemViewType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return getViewBinder(viewType).createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return getViewBinder(getItemViewType(position)).bindViewHolder(getItem(position), holder)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        getViewBinder(holder.itemViewType).onViewRecycled(holder)
        super.onViewRecycled(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        getViewBinder(holder.itemViewType).onViewDetachedFromWindow(holder)
        super.onViewDetachedFromWindow(holder)
    }
}