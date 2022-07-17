package com.deepspace.core.widgets.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import com.deepspace.core.baseWidget.WidgetView
import com.deepspace.core.databinding.ViewNavSectionBinding
import com.deepspace.core.models.Cta
import com.deepspace.core.utils.applyToTextView
import com.deepspace.core.utils.dpToPx
import com.deepspace.core.utils.goneIfVisible
import com.deepspace.core.widgets.configs.NavSectionWidgetConfig

class NavSectionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), WidgetView<NavSectionWidgetConfig> {

    private val binding = ViewNavSectionBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    override fun updateView(widgetConfig: NavSectionWidgetConfig) {
        addMenu(widgetConfig.widgetData?.leftCtas)
        addMenu(widgetConfig.widgetData?.rightCtas)
        widgetConfig.widgetData?.title.applyToTextView(binding.title)
    }

    private fun addMenu(list: List<Cta>?) {
        if (list == null) binding.leftOptions.goneIfVisible()
        list?.forEach { item ->
            binding.leftOptions.addView(prepareMenuItem(item))
        }
    }

    private fun prepareMenuItem(cta: Cta): View {
        val imageView = ImageView(context)
        imageView.updateLayoutParams {
            height = cta.icon?.height ?: 24.dpToPx(context).toInt()
            width = cta.icon?.width ?: 24.dpToPx(context).toInt()
        }
        //todo: load image via url -> investigate on libraries and then add
        //todo: create a common click listener to handle all the screens
        return imageView
    }

    override fun updateViewWithPayload(widgetConfig: NavSectionWidgetConfig, payload: Any) {
        if (payload is NavSectionWidgetConfig) {
            updateView(payload)
        }
    }

}