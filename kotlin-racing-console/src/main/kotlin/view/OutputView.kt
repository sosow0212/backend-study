package com.view

import com.model.dto.CarResponse

interface OutputView {

    fun printWinners(winners: List<CarResponse>)
}
