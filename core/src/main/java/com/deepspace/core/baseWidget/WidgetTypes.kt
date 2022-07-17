package com.deepspace.core.baseWidget

enum class WidgetTypes(val widgetTemplateName: String,val widgetId: Int) {
    NAV_SECTION_WIDGET("nav_section_widget",1),
    TEXT_VIEW_WIDGET("text_view_widget",2),
    IMAGE_VIEW_WIDGET("image_view_widget",3),
    SWITCH_VIEW_WIDGET("switch_view_widget",4),
    BUTTON_WIDGET("button_widget",5)
}