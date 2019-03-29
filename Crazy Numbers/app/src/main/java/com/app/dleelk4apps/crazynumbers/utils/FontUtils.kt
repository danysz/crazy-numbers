package com.app.dleelk4apps.crazynumbers.utils

import android.content.Context
import android.graphics.Typeface

/**
 * Created by OmarAyed on 4/4/18.
 */

class FontUtils {

    companion object {

        fun getFont(context: Context) : Typeface {
            return Typeface.createFromAsset(context.assets, "comic.otf")
        }
    }
}