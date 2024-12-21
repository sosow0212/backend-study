package hexagonal.application.port.`in`

import hexagonal.application.port.`in`.command.RacingGameSaveCommand
import hexagonal.application.port.out.dto.CarOutboundResponse
import hexagonal.domain.RacingGame

interface RacingGameUseCase {

    fun save(racingGameSaveCommand: RacingGameSaveCommand): RacingGame

    fun start()

    fun findWinners(): List<CarOutboundResponse>
}
