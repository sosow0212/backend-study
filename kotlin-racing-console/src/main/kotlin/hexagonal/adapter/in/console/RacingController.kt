package hexagonal.adapter.`in`.console

import hexagonal.adapter.`in`.console.view.InputView
import hexagonal.application.port.`in`.command.RacingGameSaveCommand
import hexagonal.application.port.`in`.RacingGameUseCase
import hexagonal.application.port.out.view.OutputView

class RacingController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val racingGameUseCase: RacingGameUseCase,
) {

    fun run() {
        saveRacingCars()
        racingGameUseCase.start()
        responseGameResult()
    }

    private fun saveRacingCars() {
        val names = inputView.requestCarNames()
        val tryCount = inputView.requestTryCount()
        racingGameUseCase.save(RacingGameSaveCommand(names, tryCount))
    }

    private fun responseGameResult() {
        val winners = racingGameUseCase.findWinners()
        outputView.printWinners(winners)
    }
}
