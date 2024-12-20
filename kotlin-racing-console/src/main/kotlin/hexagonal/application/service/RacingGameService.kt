package hexagonal.application.service

import hexagonal.application.command.RacingGameSaveCommand
import hexagonal.application.port.`in`.RacingGameUseCase
import hexagonal.application.port.out.dto.CarOutboundResponse
import hexagonal.application.port.out.repository.CarRepository
import hexagonal.domain.RacingGame
import hexagonal.domain.service.FuelGenerator
import hexagonal.exception.RacingGameNotFoundException

class RacingGameService(
    private val carRepository: CarRepository,
    private val fuelGenerator: FuelGenerator,
) : RacingGameUseCase {

    override fun save(racingGameSaveCommand: RacingGameSaveCommand): RacingGame {
        val racingGame = RacingGame.of(
            names = racingGameSaveCommand.names,
            tryCount = racingGameSaveCommand.tryCount
        )

        carRepository.save(racingGame)
        return racingGame
    }

    override fun start() {
        val racingGame = findRacingGame()
        racingGame.start(fuelGenerator)
    }

    private fun findRacingGame() = carRepository.find()
        ?: throw RacingGameNotFoundException()

    override fun findWinners(): List<CarOutboundResponse> {
        val racingGame = findRacingGame()
        return racingGame.findWinners()
            .map { CarOutboundResponse(it.name, it.moveCount) }
    }
}
