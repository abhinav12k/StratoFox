package com.deepspace.core.utils

import android.content.Context
import android.util.DisplayMetrics

fun Number.dpToPx(context: Context): Float {
    return convertDpToPixel(this.toFloat(), context)
}

private fun convertDpToPixel(dp: Float, context: Context): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}