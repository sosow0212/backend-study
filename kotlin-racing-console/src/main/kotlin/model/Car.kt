package com.model

import com.model.vo.MoveCount
import com.model.vo.Name

class Car private constructor(
    val name: Name,
    val moveCount: MoveCount
) {

    fun move(fuel: Int) {
        moveCount.move(fuel)
    }

    companion object {
        fun from(name: String): Car {
            return Car(name = Name(name), moveCount = MoveCount.createDefault())
        }
    }
}
