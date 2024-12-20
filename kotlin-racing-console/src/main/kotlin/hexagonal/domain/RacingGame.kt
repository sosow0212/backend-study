package hexagonal.domain

import com.hexagonal.model.dto.CarDomainResponse
import com.hexagonal.model.vo.TryCount
import hexagonal.domain.service.FuelGenerator

class RacingGame private constructor(
    private val cars: Cars,
    private val tryCount: TryCount
) {

    fun start(fuelGenerator: FuelGenerator) {
        repeat(tryCount.value) {
            cars.startRace(fuelGenerator)
        }
    }

    fun findWinners(): List<CarDomainResponse> {
        return cars.getWinners()
    }

    companion object {
        fun of(names: List<String>, tryCount: Int) = RacingGame(
            cars = Cars.from(names),
            tryCount = TryCount(tryCount)
        )
    }
}
