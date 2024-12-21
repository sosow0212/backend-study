package com.mvc.controller

import com.mvc.model.FuelGenerator
import com.mvc.model.RacingGame
import com.mvc.view.InputView
import com.mvc.view.OutputView

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
