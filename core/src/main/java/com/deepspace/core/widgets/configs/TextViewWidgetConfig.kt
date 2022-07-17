package com.deepspace.core.widgets.configs

import com.deepspace.core.models.TextData
import com.deepspace.core.baseWidget.WidgetConfig
import com.deepspace.core.baseWidget.WidgetTypes
import com.squareup.moshi.Json

data class TextViewWidgetConfig(
    @Json(name = "widget_properties") val widgetData: TextData? = null
) : WidgetConfig() {
    override fun getWidgetTemplateName(): String {
        return WidgetTypes.TEXT_VIEW_WIDGET.widgetTemplateName
    }
}