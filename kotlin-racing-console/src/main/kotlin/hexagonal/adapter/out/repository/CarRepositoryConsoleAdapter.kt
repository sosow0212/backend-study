package hexagonal.adapter.out.repository

import hexagonal.application.port.out.repository.CarRepository
import hexagonal.domain.RacingGame

class CarRepositoryConsoleAdapter(
    private var racingGame: RacingGame? = null
) : CarRepository {

    override fun save(racingGame: RacingGame): RacingGame {
        this.racingGame = racingGame
        return racingGame
    }

    override fun find(): RacingGame? {
        return racingGame
    }
}
