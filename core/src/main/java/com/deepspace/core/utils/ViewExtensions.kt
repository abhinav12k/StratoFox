package com.deepspace.core.utils

import android.view.Gravity
import android.view.View
import android.view.View.*
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.deepspace.core.R
import com.deepspace.core.models.TextData

fun TextData?.applyToTextView(textView: TextView) {
    if (this == null || this.text.isNullOrBlank()) {
        textView.goneIfVisible()
    }
    textView.text = this?.text
//    textView.setTextAppearance()
    textView.setTextColor(this?.color ?: R.color.black)
    textView.gravity = this?.gravity ?: Gravity.NO_GRAVITY
}

fun View.goneIfVisible() {
    if (isVisible) visibility = GONE
}

fun View.visibleIfGone() {
    if (isGone) visibility = VISIBLE
}

fun View.visibleIfInVisible() {
    if (isInvisible) visibility = VISIBLE
}

fun View.invisibleIfVisible() {
    if (isVisible) visibility = INVISIBLE
}