package com.deepspace.core.models

import com.squareup.moshi.Json

data class Cta(
    @Json(name = "type") val type: CtaType? = null,
    @Json(name = "title") val title: TextData? = null,
    @Json(name = "icon") val icon: ImageData? = null,
    @Json(name = "bg_color") val bgColor: String? = null,
    @Json(name = "stroke_color") val strokeColor: String? = null,
    @Json(name = "left_drawable") val leftDrawable: ImageData? = null,
    @Json(name = "right_drawable") val rightDrawable: ImageData? = null
)

enum class CtaType{
    EXIT, NAV, BOTTOMSHEET, SHARE, FAV
}