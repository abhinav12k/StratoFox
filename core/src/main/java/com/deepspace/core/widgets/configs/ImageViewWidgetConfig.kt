package com.deepspace.core.widgets.configs

import com.deepspace.core.models.ImageData
import com.deepspace.core.baseWidget.WidgetConfig
import com.deepspace.core.baseWidget.WidgetTypes
import com.squareup.moshi.Json

data class ImageViewWidgetConfig(
    @Json(name = "widget_properties") val widgetData: ImageData? = null
): WidgetConfig() {
    override fun getWidgetTemplateName(): String {
        return WidgetTypes.IMAGE_VIEW_WIDGET.widgetTemplateName
    }
}