package com.hexagonal.model

import com.hexagonal.model.dto.CarResponse

class Cars private constructor(
    private val cars: List<Car>
) {

    fun startRace(fuelGenerator: FuelGenerator) {
        cars.forEach { car -> car.move(fuelGenerator.generate()) }
    }

    fun getWinners(): List<CarResponse> {
        val maxMoveCount = cars.maxOfOrNull { it.moveCount.value }
            ?: NOT_EXIST_WINNER_CASE_MOVE_COUNT

        return cars.filter { it.moveCount.isSameMoveCount(maxMoveCount) }
            .map { car -> CarResponse(car.name.value, car.moveCount.value) }
    }

    companion object {
        private const val NOT_EXIST_WINNER_CASE_MOVE_COUNT = 0

        fun from(names: List<String>): Cars = Cars(
            names.map(Car.Companion::from)
        )
    }
}
