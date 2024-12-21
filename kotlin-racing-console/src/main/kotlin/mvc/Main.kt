package com.mvc

import com.mvc.controller.RacingController
import com.mvc.model.RandomFuelGenerator
import com.mvc.view.console.ConsoleInputView
import com.mvc.view.console.ConsoleOutputView

fun main() {

    RacingController(
        ConsoleInputView(),
        ConsoleOutputView(),
        RandomFuelGenerator()
    ).run()
}
