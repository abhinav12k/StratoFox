package com.deepspace.core.widget

abstract class WidgetConfig {
    // widgetType -> eg "TextView", "ImageView", "SwitchView", etc.
    private val widgetType: String = getWidgetType()
    private val widgetId: Int = 0

    abstract fun getWidgetType(): String

    open fun getId(): Int = widgetId
}