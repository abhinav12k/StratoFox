package com.deepspace.core.baseWidget

interface WidgetView<C: WidgetConfig> {
    fun updateView(widgetConfig: C)
    fun updateViewWithPayload(widgetConfig: C, payload: Any)
}