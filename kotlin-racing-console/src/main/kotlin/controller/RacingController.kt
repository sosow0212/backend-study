package com.controller

import com.model.FuelGenerator
import com.model.RacingGame
import com.view.InputView
import com.view.OutputView

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
