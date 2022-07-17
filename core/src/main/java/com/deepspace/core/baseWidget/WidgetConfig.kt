package com.deepspace.core.baseWidget

abstract class WidgetConfig {
    // widgetTemplateName -> eg "text_view_widget", "image_view_widget", "switch_view_widget", etc.
    private val widgetTemplateName: String = getWidgetTemplateName()
    private val widgetId: Int = 0

    abstract fun getWidgetTemplateName(): String

    open fun getId(): Int = widgetId
}