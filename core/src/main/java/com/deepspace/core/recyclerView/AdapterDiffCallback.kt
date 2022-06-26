package com.deepspace.core.recyclerView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder

typealias BaseItemClass = Class<out Any>
typealias BaseItemViewBinder = BaseViewBinder<Any, ViewHolder>

internal class BaseDiffCallback(
    private val viewBinders: Map<BaseItemClass, BaseItemViewBinder>
) : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem::class != newItem::class) {
            return false
        }
        return viewBinders[oldItem::class.java]?.areItemsTheSame(oldItem, newItem) ?: false
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return viewBinders[oldItem::class.java]?.areContentsTheSame(oldItem, newItem) ?: false
    }

}