package com.deepspace.core.models

import com.deepspace.core.utils.Gravity
import com.squareup.moshi.Json

data class ImageData(
    @Json(name = "url") val url: String? = null,
    @Json(name = "height") val height: Int? = null,
    @Json(name = "width") val width: Int? = null,
    @Json(name = "gravity") @Gravity val gravity: Int? = null
)