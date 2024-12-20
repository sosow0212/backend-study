package com.hexagonal.controller

import com.hexagonal.model.FuelGenerator
import com.hexagonal.model.RacingGame
import com.hexagonal.view.InputView
import com.hexagonal.view.OutputView

class RacingController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val fuelGenerator: FuelGenerator
) {

    fun run() {
        val names = inputView.requestCarNames()
        val tryCount = inputView.requestTryCount()

        val racingGame = RacingGame.of(names = names, tryCount = tryCount)
        racingGame.start(fuelGenerator)
        val winners = racingGame.getWinners()

        outputView.printWinners(winners)
    }
}
