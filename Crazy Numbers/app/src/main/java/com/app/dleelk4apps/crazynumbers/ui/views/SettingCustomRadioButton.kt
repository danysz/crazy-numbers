package com.app.dleelk4apps.crazynumbers.ui.views

import android.content.ContentValues
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.RadioButton
import com.app.dleelk4apps.crazynumbers.R

/**
 * Created by OmarAyed on 05/01/2018.
 */
class SettingCustomRadioButton : RadioButton {

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
        // Load attributes
         val a = context.obtainStyledAttributes(attrs, R.styleable.SettingCustomRadioButton, defStyle, 0)
         val isCircle = a.getBoolean(R.styleable.SettingCustomRadioButton_isCircle, true)


        background = if (isCircle) {
            context.resources.getDrawable(R.drawable.btn_circle_selector)
        } else {
            context.resources.getDrawable(R.drawable.btn_rectangle_selector)
        }

      //  setTextColor(R.drawable.rbtn_textcolor_selector)
        buttonDrawable = null
        gravity = Gravity.CENTER
        isChecked = false

        var tf: Typeface? = null
        try {
            tf = Typeface.createFromAsset(context.assets, context.getString(R.string.akaDylan))
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Could not get typeface: " + e.message)
        }

        typeface = tf

        a.recycle()
    }

}

