package com.app.dleelk4apps.crazynumbers.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.app.dleelk4apps.crazynumbers.R

/**
 * TODO: document your custom view class.
 */
class GameCustomStartButtonView : LinearLayout {

    lateinit var customTextView: GameCustomTextView

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs, defStyle)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int) {

        inflate(getContext(), R.layout.custom_start_view, this)
        customTextView = findViewById(R.id.gameCustomTextView)

        // Load attributes
        val a = context.obtainStyledAttributes(attrs, R.styleable.GameCustomStartButtonView, defStyle, 0)
        val text = a.getString(R.styleable.GameCustomStartButtonView_text)
        a.recycle()

        customTextView.text = text


    }



}