package com.hexagonal.view

import com.hexagonal.model.dto.CarResponse

interface OutputView {

    fun printWinners(winners: List<CarResponse>)
}
