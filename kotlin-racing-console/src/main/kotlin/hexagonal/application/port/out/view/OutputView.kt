package hexagonal.application.port.out.view

import hexagonal.application.port.out.dto.CarOutboundResponse

interface OutputView {

    fun printWinners(winners: List<CarOutboundResponse>)
}
