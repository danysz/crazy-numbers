package com.app.dleelk4apps.crazynumbers.ui.game

import com.app.dleelk4apps.crazynumbers.data.GameGridItemData
import com.app.dleelk4apps.crazynumbers.utils.GameSettingUtils
import java.util.*

/**
 * Created by OmarAyed on 14/01/2018.
 */
class GameLogic(setting: GameSettingUtils) {

    var gameSetting = setting

    private val random = Random()
    private var numbersArray = ArrayList<Int>()
    private var operatorsArray = ArrayList<String>()

    fun buildMatrix(): ArrayList<GameGridItemData> {
        numbersArray.clear()
        operatorsArray.clear()
        generateNumbers()
        generateOperators()

        return buildGameData()
    }

    private fun buildGameData(): ArrayList<GameGridItemData> {
        val gameListData = ArrayList<GameGridItemData>()
        numbersArray.forEach{ i ->
            val item = GameGridItemData(i, GameGridItemData.GameItemType.Number)
            gameListData.add(item)
        }

        operatorsArray.forEach{ i ->
            val item = GameGridItemData(i, GameGridItemData.GameItemType.Operator)
            gameListData.add(item)
        }

        gameListData.shuffle()

        for (i in 0 until gameListData.size) {
            run {
                gameListData[i].position = i
            }
        }

        return gameListData
    }

    private fun generateNumbers() {
        val size = gameSetting.getNumbersCount() - 1
        for (i in 0..size) {
            numbersArray.add(generateNumber())
        }
    }

    private fun generateOperators() {
        val size = gameSetting.getOperatorsCount() - 1
        for (i in 0..size) {
            operatorsArray.add(generateOperator())
        }
    }

    private fun generateNumber(): Int {
        val range = gameSetting.getRange()
        return random.nextInt(range.last - range.first) + range.first
    }

    private fun generateOperator(): String {
        val operators = gameSetting.getOperatorsType()
        val index = random.nextInt(operators.length)
        return operators[index].toString()
    }

    fun ClosedRange<Char>.random(): Char =
            (Random().nextInt(endInclusive.toInt() + 1 - start.toInt()) + start.toInt()).toChar()
}