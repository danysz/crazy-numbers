package com.app.dleelk4apps.crazynumbers.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout



/**
 * Created by OmarAyed on 4/2/18.
 */
class SquareFrameLayout : FrameLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}