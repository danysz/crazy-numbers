package com.app.dleelk4apps.crazynumbers.ui.views

import android.content.Context
import android.support.annotation.Dimension.SP
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.app.dleelk4apps.crazynumbers.R
import com.app.dleelk4apps.crazynumbers.utils.FontUtils
import com.app.dleelk4apps.crazynumbers.utils.GameSettingUtils.GameDifficultyType
import com.app.dleelk4apps.crazynumbers.utils.GameSettingUtils.GameMatrixType

class GameAnswerTextView : LinearLayout {

    var gameItemCustomTextView: AppCompatTextView? = null
    var itemPosition = -1

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

        inflate(context, R.layout.game_grid_item_row, this)
        gameItemCustomTextView = findViewById(R.id.gameItemCustomTextView)
        gameItemCustomTextView?.typeface = FontUtils.getFont(context)
        gameItemCustomTextView?.setTextSize(SP, 18.0f)
        gameItemCustomTextView?.gravity = Gravity.CENTER
        gameItemCustomTextView?.textAlignment = View.TEXT_ALIGNMENT_CENTER

        this.gravity = Gravity.CENTER


    }

    public fun buildView(difficultyType: GameDifficultyType, matrixType: GameMatrixType) {

    }

}
