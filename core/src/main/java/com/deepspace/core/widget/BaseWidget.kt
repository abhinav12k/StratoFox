package com.deepspace.core.widget

import android.content.Context
import android.view.View

abstract class BaseWidget<V, C : WidgetConfig> where V : View, V : WidgetView<C> {

    var view: V
    private var config: C? = null

    protected constructor(widgetView: V) {
        view = widgetView
    }

    constructor(context: Context) {
        view = createView(context)
    }

    protected abstract fun createView(context: Context): V
    abstract fun getWidgetType(): String

    private fun setWidgetConfig(widgetConfig: C) {
        this.config = widgetConfig
    }

    private fun updateView(widgetConfig: C) {
        view.updateView(widgetConfig)
    }

    private fun isValidConfig(widgetConfig: C?): Boolean {
        return getWidgetType() == widgetConfig?.getWidgetType()
    }

    fun updateWidget(widgetConfig: C?) {
        if (widgetConfig == null) {
            view.visibility = View.GONE
            return
        }
        setWidgetConfig(widgetConfig)
        updateView(widgetConfig)
    }

    fun updateWidgetWithPayload(widgetConfig: C?, payload: Any?) {
        if (payload == null) {
            updateWidget(widgetConfig)
            return
        }
        if (widgetConfig == null || !isValidConfig(widgetConfig)) {
            view.visibility = View.GONE
            return
        }
        setWidgetConfig(widgetConfig)
        updateViewWithPayload(widgetConfig, payload)
    }

    private fun updateViewWithPayload(widgetConfig: C, payload: Any) {
        view.updateViewWithPayload(widgetConfig, payload)
    }

}