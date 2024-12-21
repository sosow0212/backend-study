package hexagonal.adapter.out.view

import hexagonal.application.port.out.dto.CarOutboundResponse
import hexagonal.application.port.out.view.OutputView

class ConsoleOutputViewAdapter : OutputView {

    override fun printWinners(winners: List<CarOutboundResponse>) {
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
