package com.deepspace.core.widgets.configs

import com.deepspace.core.models.Cta
import com.deepspace.core.models.TextData
import com.deepspace.core.baseWidget.WidgetConfig
import com.deepspace.core.baseWidget.WidgetTypes
import com.squareup.moshi.Json

data class NavSectionWidgetConfig(
    @Json(name = "widget_properties") val widgetData: NavSectionWidgetData? = null
) : WidgetConfig() {
    override fun getWidgetTemplateName(): String {
        return WidgetTypes.NAV_SECTION_WIDGET.widgetTemplateName
    }
}

data class NavSectionWidgetData(
    @Json(name = "leftCtas") val leftCtas: List<Cta>? = null,
    @Json(name = "title") val title: TextData? = null,
    @Json(name = "rightCtas") val rightCtas: List<Cta>? = null
)