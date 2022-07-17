package com.deepspace.core.models

import com.deepspace.core.utils.Gravity
import com.deepspace.core.utils.HexColor
import com.squareup.moshi.Json

data class TextData(
    @Json(name = "text") val text: String? = null,
    @Json(name = "color") @HexColor val color: Int? = null,
    @Json(name = "font") val font: String? = null,
    @Json(name = "gravity") @Gravity val gravity: Int? = null
)