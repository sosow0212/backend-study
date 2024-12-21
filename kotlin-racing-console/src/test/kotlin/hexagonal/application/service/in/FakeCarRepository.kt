package hexagonal.application.service.`in`

import hexagonal.application.port.out.repository.CarRepository
import hexagonal.domain.RacingGame

class FakeCarRepository(
    private var racingGame: RacingGame? = null,
) : CarRepository {

    override fun save(racingGame: RacingGame): RacingGame {
        this.racingGame = racingGame
        return racingGame
    }

    override fun find(): RacingGame? {
        return racingGame
    }
}
