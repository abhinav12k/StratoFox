package com.deepspace.core.recyclerView

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseViewBinder<M, in VH: ViewHolder>(val modelClass: Class<out M>): DiffUtil.ItemCallback<M>() {
    abstract fun createViewHolder(parent: ViewGroup): ViewHolder
    abstract fun bindViewHolder(model: M, viewHolder: VH)
    abstract fun getItemViewType(): Int

    // Having these as non abstract because not all the viewBinders are required to implement them.
    open fun onViewRecycled(viewHolder: VH) = Unit
    open fun onViewDetachedFromWindow(viewHolder: VH) = Unit
}