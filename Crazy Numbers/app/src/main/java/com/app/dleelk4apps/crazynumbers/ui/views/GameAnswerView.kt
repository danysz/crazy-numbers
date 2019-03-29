package com.app.dleelk4apps.crazynumbers.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.app.dleelk4apps.crazynumbers.R
import com.app.dleelk4apps.crazynumbers.utils.GameSettingUtils.GameDifficultyType
import com.app.dleelk4apps.crazynumbers.utils.GameSettingUtils.GameMatrixType

class GameAnswerView : LinearLayout {

    var answerViewContainer: LinearLayout? = null

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

        inflate(getContext(), R.layout.game_answer_view, this)
        answerViewContainer = findViewById(R.id.answerViewContainer)

    }

    public fun buildView(difficultyType: GameDifficultyType, matrixType: GameMatrixType) {

    }

}
