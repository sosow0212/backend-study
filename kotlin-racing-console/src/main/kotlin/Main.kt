package com

import com.controller.RacingController
import com.model.RandomFuelGenerator
import com.view.console.ConsoleInputView
import com.view.console.ConsoleOutputView

fun main() {

    RacingController(
        ConsoleInputView(),
        ConsoleOutputView(),
        RandomFuelGenerator()
    ).run()
}
