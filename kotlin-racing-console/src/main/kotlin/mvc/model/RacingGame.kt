package com.mvc.model

import com.mvc.model.dto.CarResponse
import com.mvc.model.vo.TryCount

class RacingGame private constructor(
    private val cars: Cars,
    private val tryCount: TryCount
) {

    fun start(fuelGenerator: FuelGenerator) {
        repeat(tryCount.value) {
            cars.startRace(fuelGenerator)
        }
    }

    fun getWinners(): List<CarResponse> {
        return cars.getWinners()
    }

    companion object {
        fun of(names: List<String>, tryCount: Int) = RacingGame(
            cars = Cars.from(names),
            tryCount = TryCount(tryCount)
        )
    }
}
