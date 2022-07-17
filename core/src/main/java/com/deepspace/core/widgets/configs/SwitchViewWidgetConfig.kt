package com.deepspace.core.widgets.configs

import com.deepspace.core.models.Cta
import com.deepspace.core.baseWidget.WidgetConfig
import com.deepspace.core.baseWidget.WidgetTypes
import com.squareup.moshi.Json

data class SwitchViewWidgetConfig(
    @Json(name = "widget_properties") val widgetData: SwitchViewWidgetData? = null
) : WidgetConfig() {
    override fun getWidgetTemplateName(): String {
        return WidgetTypes.SWITCH_VIEW_WIDGET.widgetTemplateName
    }
}

data class SwitchViewWidgetData(
    @Json(name = "cta1") val cta1: Cta? = null,
    @Json(name = "cta2") val cta2: Cta? = null
)
