package com.deepspace.core.recyclerView

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deepspace.core.utils.dpToPx
import com.deepspace.core.widget.BaseWidget

abstract class BaseWidgetViewHolder<BW : BaseWidget<*, *>>(
    widget: BW,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    topMargin: Int = 0,
    bottomMargin: Int = 0
) : RecyclerView.ViewHolder(widget.view) {

    init {
        val layoutParams: ViewGroup.MarginLayoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        if (marginStart > 0) {
            val margin = marginStart.dpToPx(itemView.context).toInt()
            layoutParams.marginStart = margin
        }
        if (marginEnd > 0) {
            val margin = marginEnd.dpToPx(itemView.context).toInt()
            layoutParams.marginEnd = margin
        }
        if (topMargin > 0) {
            val margin = topMargin.dpToPx(itemView.context).toInt()
            layoutParams.topMargin = margin
        }
        if (bottomMargin > 0) {
            val margin = bottomMargin.dpToPx(itemView.context).toInt()
            layoutParams.bottomMargin = margin
        }
        widget.view.layoutParams = layoutParams
    }

}