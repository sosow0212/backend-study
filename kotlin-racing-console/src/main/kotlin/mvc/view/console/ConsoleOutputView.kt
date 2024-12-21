package com.mvc.view.console

import com.mvc.model.dto.CarResponse
import com.mvc.view.OutputView

class ConsoleOutputView : OutputView {

    override fun printWinners(winners: List<CarResponse>) {
        if (winners.isEmpty()) {
            println("우승자가 없습니다.")
            return
        }

        val moveCount = winners.first().moveCount
        val names = winners.joinToString(", ") { it.name }
        val output = "우승을 위해 움직인 거리: $moveCount, 우승자: $names"

        println(output)
    }
}
