package hexagonal.domain

import com.hexagonal.model.Car
import com.hexagonal.model.dto.CarDomainResponse
import hexagonal.domain.service.FuelGenerator

class Cars private constructor(
    private val cars: List<Car>
) {

    fun startRace(fuelGenerator: FuelGenerator) {
        cars.forEach { car -> car.move(fuelGenerator.generate()) }
    }

    fun getWinners(): List<CarDomainResponse> {
        val maxMoveCount = cars.maxOfOrNull { it.moveCount.value }
            ?: NOT_EXIST_WINNER_CASE_MOVE_COUNT

        return cars.filter { it.moveCount.isSameMoveCount(maxMoveCount) }
            .map { car -> CarDomainResponse(car.name.value, car.moveCount.value) }
    }

    companion object {
        private const val NOT_EXIST_WINNER_CASE_MOVE_COUNT = 0

        fun from(names: List<String>): Cars = Cars(
            names.map(Car.Companion::from)
        )
    }
}