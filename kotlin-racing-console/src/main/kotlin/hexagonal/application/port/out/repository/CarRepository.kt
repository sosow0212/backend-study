package hexagonal.application.port.out.repository

import hexagonal.domain.RacingGame

interface CarRepository {

    fun save(racingGame: RacingGame): RacingGame

    fun find(): RacingGame?
}
