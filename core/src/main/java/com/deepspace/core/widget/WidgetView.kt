package com.deepspace.core.widget

interface WidgetView<C: WidgetConfig> {
    fun updateView(widgetConfig: C)
    fun updateViewWithPayload(widgetConfig: C, payload: Any)
}