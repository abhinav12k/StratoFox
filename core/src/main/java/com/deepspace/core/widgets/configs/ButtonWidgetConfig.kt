package com.deepspace.core.widgets.configs

import com.deepspace.core.models.Cta
import com.deepspace.core.baseWidget.WidgetConfig
import com.deepspace.core.baseWidget.WidgetTypes
import com.squareup.moshi.Json

data class ButtonWidgetConfig(
    @Json(name = "widget_properties") val widgetData: Cta? = null
): WidgetConfig(){
    override fun getWidgetTemplateName(): String {
        return WidgetTypes.BUTTON_WIDGET.widgetTemplateName
    }
}