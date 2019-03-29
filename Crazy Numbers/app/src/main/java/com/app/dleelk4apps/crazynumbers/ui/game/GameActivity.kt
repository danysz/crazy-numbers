package com.app.dleelk4apps.crazynumbers.ui.game

import android.os.Bundle
import android.app.Activity
import android.graphics.Color
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.LinearLayout
import com.app.dleelk4apps.crazynumbers.App.CrazyNumbersApplication
import com.app.dleelk4apps.crazynumbers.R
import com.app.dleelk4apps.crazynumbers.data.GameGridItemData
import com.app.dleelk4apps.crazynumbers.data.GamerData
import com.app.dleelk4apps.crazynumbers.ui.game.adapter.GameGridViewAdapter
import com.app.dleelk4apps.crazynumbers.ui.views.GameAnswerTextView
import com.app.dleelk4apps.crazynumbers.utils.*
import com.app.dleelk4apps.crazynumbers.utils.GameSettingUtils.GameDifficultyType
import com.app.dleelk4apps.crazynumbers.utils.GameSettingUtils.GameMatrixType
import kotlinx.android.synthetic.main.activity_game.*
import android.view.animation.GridLayoutAnimationController
import android.view.animation.AnimationUtils



class GameActivity : Activity() {

    val scorePoint = 50
    private lateinit var gameGridView: GridView
    private lateinit var gameBottomLinearLayout: LinearLayout

    private lateinit var gameAdapter: GameGridViewAdapter

    private var gameSetting = GameSettingUtils(GameDifficultyType.Easy, GameMatrixType.Mat3x3)
    private var gamerData = GamerData()
    private var difficultyType = GameDifficultyType.Easy
    private var matrixType = GameMatrixType.Mat3x3

    var answers = 0
    var gameListData = ArrayList<GameGridItemData>()
    var lastGameItemType: GameGridItemData.GameItemType? = null
    var selectedLeftData = ArrayList<GameGridItemData>()
    var selectedRightData = ArrayList<GameGridItemData>()

    val equalData = GameGridItemData("=", GameGridItemData.GameItemType.Operator)
    private var gameLogic = GameLogic(gameSetting)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.app.dleelk4apps.crazynumbers.R.layout.activity_game)

        initViews()
        initGameData()
        buildMatrix()
        initBottomView()
        gameGridView.numColumns = gameSetting.matrixType.value
        gameAdapter = GameGridViewAdapter(this, gameListData, gameSetting)
        gameGridView.adapter = gameAdapter
        val animation = AnimationUtils.loadAnimation(this, com.app.dleelk4apps.crazynumbers.R.anim.grid_view_anim)
        val controller = GridLayoutAnimationController(animation, .2f, .2f)
        gameGridView.layoutAnimation = controller
        gameGridView.onItemClickListener = GameGridViewClickListener()

        object : CountDownTimer(3600000, 1000) {
            internal var startTime = System.currentTimeMillis()
            override fun onTick(millisUntilFinished: Long) {
                val timePassed = System.currentTimeMillis() - startTime
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                timeTextView.text = "$minutes : $seconds" //((36000000 - timePassed) / 1000).toString()
            }

            override fun onFinish() {

            }
        }.start()

        refreshView.setOnClickListener {
            gameListData.forEach {
                it.isUsed = false
                it.isSelected = false
            }

            val minusPoints = answers * scorePoint
            var points = (pointsTextView.text as String).toInt()
            points -= minusPoints
            if(points < 0) {
                points = 0
            }
            pointsTextView.text = points.toString()

            answers = 0
            selectedRightData.clear()
            selectedLeftData.clear()
            lastGameItemType = null
            updateBottomView()
            gameAdapter.refreshView()
        }



    }

    fun refresh() {

    }

    private fun buildMatrix() {
        gameListData = gameLogic.buildMatrix()
    }

    private fun initViews() {
        gameBottomLinearLayout = findViewById(com.app.dleelk4apps.crazynumbers.R.id.gameBottomLinearLayout)
        gameGridView = findViewById(com.app.dleelk4apps.crazynumbers.R.id.gameGridView)
    }


    private fun initGameData() {
        val prefs = CrazyNumbersApplication.appInstance.getPrefsInstance()

        val value = prefs.getInteger(Constants.GAME_DIFFICULTY_KEY)
        difficultyType = GameDifficultyType.from(value)

        val matrixValue = prefs.getInteger(Constants.GAME_MATRIX_KEY)
        matrixType = GameMatrixType.from(matrixValue)

        gamerData = prefs.getGamerData()

        gameSetting = GameSettingUtils(difficultyType, matrixType)

        gameLogic.gameSetting = gameSetting
    }

    private fun initBottomView() {
        for (i in 0 until 7) {
            val view = GameAnswerTextView(this)
            view.gameItemCustomTextView?.text = ""
            if (i == 3) {
                equalData.position = 3
                view.gameItemCustomTextView?.text = equalData.value as String
                //view.gameItemCustomTextView?.setBackgroundColor(Color.GREEN)
            }
            view.gameItemCustomTextView?.setBackgroundColor(Color.TRANSPARENT)
            gameBottomLinearLayout.addView(view, ViewGroup.LayoutParams(50.px, 100.px))
        }
    }

    private fun saveGameData() {
        val prefs = CrazyNumbersApplication.appInstance.getPrefsInstance()
        prefs.setInteger(Constants.GAME_DIFFICULTY_KEY, difficultyType.value)
        prefs.setInteger(Constants.GAME_MATRIX_KEY, matrixType.value)
        prefs.setGamerData(gamerData)
    }


    fun addViewAtIndex(index: Int) {
        val itemData = gameListData[index]

        // val view = GameAnswerTextView(this)
//         view.gameItemCustomTextView?.text = when(itemData.gameItemType) {
//             GameGridItemData.GameItemType.Number ->  (itemData.value as Int).toString()
//             GameGridItemData.GameItemType.Operator -> itemData.value as String
//         }
        // view.itemPosition = itemData.position!!
        // gameBottomLinearLayout.addView(view, ViewGroup.LayoutParams(gameBottomLinearLayout.height / 2, gameBottomLinearLayout.height))

        if (selectedLeftData.size < 3) {
            selectedLeftData.add(itemData)
        } else {
            selectedRightData.add(itemData)
        }

        lastGameItemType = itemData.gameItemType
        gameListData[index].isSelected = true

        if(selectedLeftData.size == 3 && selectedRightData.size == 0) {
            lastGameItemType = null
        }

        updateBottomView()

        if(selectedLeftData.size == 3 && selectedRightData.size == 3) {
            checkTheAnswer()
        }

    }

    fun checkTheAnswer() {

        val leftOpr = selectedLeftData[1].value as String
        val rightOpr = selectedRightData[1].value as String

        val leftRes = getResult(selectedLeftData[0].value as Int, selectedLeftData[2].value as Int, leftOpr)
        val rightRes = getResult(selectedRightData[0].value as Int, selectedRightData[2].value as Int, rightOpr)

        if (leftRes == rightRes) {


            var points = (pointsTextView.text as String).toInt()
            points += scorePoint
            pointsTextView.text = points.toString()
            gameListData.forEach {
                if(it.isSelected) {
                    it.isUsed = true
                }
            }

            answers += 1

            if(matrixType.value - 2 == answers) {
                answers = 0
                gameListData.clear()
                gameListData = gameLogic.buildMatrix()
                gameAdapter.updateData(gameListData)
            }

        } else {
            gameListData.forEach {
                if(!it.isUsed) {
                    it.isSelected = false
                }
            }
        }

        selectedRightData.clear()
        selectedLeftData.clear()
        lastGameItemType = null
        updateBottomView()
        gameAdapter.refreshView()
    }

    fun getResult(num1: Int, num2: Int, opr: String) : Int {

        return when(opr) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "/" -> num1 / num2
            "*" -> num1 * num2
            else -> 0
        }
    }

    fun updateBottomView() {
        for (i in 0 until gameBottomLinearLayout.childCount) {
            if (i != 3) {
                val view = gameBottomLinearLayout.getChildAt(i) as GameAnswerTextView
                if (i < selectedLeftData.size) {
                    view.itemPosition = selectedLeftData[i].position!!
                    view.gameItemCustomTextView?.text = when (selectedLeftData[i].gameItemType) {
                        GameGridItemData.GameItemType.Number -> (selectedLeftData[i].value as Int).toString()
                        GameGridItemData.GameItemType.Operator -> selectedLeftData[i].value as String
                    }
                } else if (i > 3  && i >= selectedLeftData.size && selectedRightData.size > i-4) {
                    view.itemPosition = selectedRightData[i - 4].position!!
                    view.gameItemCustomTextView?.text = when (selectedRightData[i - 4].gameItemType) {
                        GameGridItemData.GameItemType.Number -> (selectedRightData[i - 4].value as Int).toString()
                        GameGridItemData.GameItemType.Operator -> selectedRightData[i - 4].value as String
                    }
                } else {
                    view.itemPosition = -1
                    view.gameItemCustomTextView?.text = ""
                }
            }
        }
    }


    fun removeViewAtIndex(index: Int): Int {
        val itemData = gameListData[index]

        for (i in 0 until selectedLeftData.size + selectedRightData.size) {
            //val view = gameBottomLinearLayout.getChildAt(i) as GameAnswerTextView
            if (itemData.position == selectedLeftData[i].position) {
               // gameBottomLinearLayout.removeViewAt(i)
                selectedLeftData.removeAt(i)
                gameListData[index].isSelected = false
                //      lastGameItemType = selectedData.lastOrNull()?.gameItemType
                return i
            } else if (itemData.position == selectedRightData[i].position) {
                selectedRightData.removeAt(i)
                gameListData[index].isSelected = false
                return i
            }

            updateBottomView()

        }

        // selectedData.remove(itemData)
        // lastGameItemType = selectedData.lastOrNull()?.gameItemType
        return -1
    }


    inner class GameGridViewClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            val itemData = gameListData[p2]
            if (!itemData.isSelected && !itemData.isUsed) {
                if ((lastGameItemType == null &&
                                itemData.gameItemType == GameGridItemData.GameItemType.Number) ||
                        (itemData.gameItemType == GameGridItemData.GameItemType.Operator && lastGameItemType == GameGridItemData.GameItemType.Number) ||
                        itemData.gameItemType == GameGridItemData.GameItemType.Number && lastGameItemType == GameGridItemData.GameItemType.Operator) {

                    addViewAtIndex(p2)
                    gameAdapter.refreshView()

                    //gameListData[p2].isSelected = !gameListData[p2].isSelected
                    // gameAdapter.refreshView()
                }


            } else {
//                if (itemData.gameItemType == GameGridItemData.GameItemType.Operator) {
//                    val removedIndex = removeViewAtIndex(p2)
////                    val lastItem = selectedData.lastOrNull()
////                    if(lastItem != null && removedIndex < selectedData.size && removedIndex > 0) {
////                        val prevData = selectedData[removedIndex - 1]
////                        removeViewAtIndex(prevData.position!!)
////                       // gameListData[removedIndex-1].isSelected = !gameListData[removedIndex-1].isSelected
////                    }
//
//                } else {
//                    val removedIndex = removeViewAtIndex(p2)
////                    val lastItem = selectedData.lastOrNull()
////                    if(lastItem != null && lastItem.position != itemData.position && removedIndex < selectedData.size) {
////                        val prevData = selectedData[removedIndex]
////                        removeViewAtIndex(prevData.position!!)
////                      //  gameListData[tmp+1].isSelected = !gameListData[tmp].isSelected
////                    }
//                }
           //     gameAdapter.refreshView()
                //gameListData[p2].isSelected = !gameListData[p2].isSelected
//                for (i in 0 until gameListData.size) {
//                    gameListData[i].isSelected = selectedData.contains(gameListData[i])
//                }

            }

        }
    }
}











