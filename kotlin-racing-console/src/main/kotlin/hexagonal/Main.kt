package com.hexagonal

import hexagonal.adapter.out.view.ConsoleInputViewAdapter
import hexagonal.adapter.out.view.ConsoleOutputViewAdapter
import hexagonal.adapter.`in`.console.RacingController
import hexagonal.adapter.out.repository.CarRepositoryConsoleAdapter
import hexagonal.adapter.out.domainservice.RandomFuelGeneratorAdapter
import hexagonal.application.service.RacingGameService

fun main() {

    RacingController(
        ConsoleInputViewAdapter(),
        ConsoleOutputViewAdapter(),
        RacingGameService(
            carRepository = CarRepositoryConsoleAdapter(),
            fuelGenerator = RandomFuelGeneratorAdapter()
        )
    ).run()
}
