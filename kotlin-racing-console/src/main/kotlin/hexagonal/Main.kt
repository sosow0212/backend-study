package com.hexagonal

import com.hexagonal.controller.RacingController
import com.hexagonal.model.RandomFuelGenerator
import com.hexagonal.view.console.ConsoleInputView
import com.hexagonal.view.console.ConsoleOutputView

fun main() {

    RacingController(
        ConsoleInputView(),
        ConsoleOutputView(),
        RandomFuelGenerator()
    ).run()
}
