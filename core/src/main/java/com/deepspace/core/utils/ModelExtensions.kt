package com.deepspace.core.utils

import com.deepspace.core.models.Alignment
import com.deepspace.core.models.Alignment.*

fun Alignment?.getGravityFromAlignment(): Int {
    return when(this) {
        CENTER -> android.view.Gravity.CENTER
        TOP -> android.view.Gravity.TOP
        BOTTOM -> android.view.Gravity.BOTTOM
        START -> android.view.Gravity.START
        END -> android.view.Gravity.END
        else -> android.view.Gravity.START
    }
}