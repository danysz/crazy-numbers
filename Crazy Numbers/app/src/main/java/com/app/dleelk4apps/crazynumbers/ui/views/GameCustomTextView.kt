package com.app.dleelk4apps.crazynumbers.ui.views

import android.content.ContentValues.TAG
import android.content.Context
import android.util.AttributeSet

import com.app.dleelk4apps.crazynumbers.R
import android.graphics.Typeface
import android.util.Log
import android.widget.TextView


/**
 * TODO: document your custom view class.
 */
class GameCustomTextView : TextView {


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
        val a = context.obtainStyledAttributes(attrs, R.styleable.GameCustomTextView, defStyle, 0)
        val customFont = a.getString(R.styleable.GameCustomTextView_customFont)
        setCustomFont(context, customFont)
        a.recycle()
    }

   private fun setCustomFont(ctx: Context, asset: String): Boolean {
       var tf: Typeface?
       try {
            tf = Typeface.createFromAsset(ctx.assets, asset)
        } catch (e: Exception) {
            Log.e(TAG, "Could not get typeface: " + e.message)
            return false
        }

       typeface = tf
        return true
    }

}
