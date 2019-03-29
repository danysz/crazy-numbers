package com.app.dleelk4apps.crazynumbers.utils

/**
 * Created by OmarAyed on 04/01/2018.
 */


class GameSettingUtils(var difficultyType: GameDifficultyType, var matrixType: GameMatrixType) {

    val operators = "+-*/"

    enum class GameDifficultyType(var value: Int) {
        Easy(1),
        Normal(2),
        Medium(3),
        Hard(4);

        companion object {
            fun from(findValue: Int): GameDifficultyType = GameDifficultyType.values().first { it.value == findValue }
        }
    }

    enum class GameMatrixType(val value: Int) {
        Mat3x3(3),
        Mat4x4(4),
        Mat5x5(5),
        Mat6x6(6),
        Mat7x7(7);

        companion object {
            fun from(findValue: Int): GameMatrixType = GameMatrixType.values().first { it.value == findValue }
        }
    }


    fun getMatrixSize(): Int {
        return matrixType.value * matrixType.value
    }

    fun getNumbersCount(): Int {
        return getMatrixSize() - getOperatorsCount()
    }

    fun getOperatorsCount(): Int {
        val value = getMatrixSize()
        return when (difficultyType) {
            GameDifficultyType.Easy, GameDifficultyType.Normal -> (value / 3)
            GameDifficultyType.Medium, GameDifficultyType.Hard -> (value / 5) * 2
        }
    }

    fun getOperatorsType(): String {
        return when (difficultyType) {
            GameDifficultyType.Easy -> "+-*/"
            GameDifficultyType.Normal -> "+-*/%"
            GameDifficultyType.Medium -> "+-*/%^s"
            GameDifficultyType.Hard -> "+-*/%^spe"
        }
    }

    fun getRange(): IntRange {
        return when (difficultyType) {
            GameDifficultyType.Easy -> IntRange(start = 1, endInclusive = 9)
            GameDifficultyType.Normal -> IntRange(start = 1, endInclusive = 100)
            GameDifficultyType.Medium -> IntRange(start = -50, endInclusive = 100)
            GameDifficultyType.Hard -> IntRange(start = -100, endInclusive = 100)
        }
    }
}