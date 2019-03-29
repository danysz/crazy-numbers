package com.app.dleelk4apps.crazynumbers.data

/**
 * Created by OmarAyed on 03/01/2018.
 */


class GameGridItemData(var value: Any, var gameItemType: GameGridItemData.GameItemType) {

    enum class GameItemType {
        Number, Operator
    }

    var position: Int? = null
    var background: Int? = null
    var isSelected: Boolean = false
    var isUsed: Boolean = false

}