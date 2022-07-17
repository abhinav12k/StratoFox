package com.deepspace.core.utils

import com.deepspace.core.models.Alignment
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class HexColor

class ColorAdapter {
    @ToJson
    fun toJson(@HexColor rgb: Int): String {
        return "#%06x".format(rgb)
    }

    @FromJson
    @HexColor
    fun fromJson(rgb: String): Int {
        return rgb.substring(1).toInt(16)
    }
}

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class Gravity

class GravityAdapter {
    @ToJson
    fun toJson(@Gravity alignment: Alignment): Alignment {
        return alignment
    }

    @FromJson
    @Gravity
    fun fromJson(alignment: Alignment): Int {
        return alignment.getGravityFromAlignment()
    }
}