package com.mvc.model.vo

data class MoveCount(
    var value: Int
) {

    fun move(fuel: Int) {
        if (fuel >= REQUIRE_MINIMUM_FUEL_TO_MOVE_FORWARD) {
            this.value++
        }
    }

    fun isSameMoveCount(moveCount: Int) = this.value == moveCount

    companion object {
        private const val DEFAULT_MOVE_COUNT = 0
        private const val REQUIRE_MINIMUM_FUEL_TO_MOVE_FORWARD = 5

        fun createDefault(): MoveCount {
            return MoveCount(DEFAULT_MOVE_COUNT)
        }
    }
}
