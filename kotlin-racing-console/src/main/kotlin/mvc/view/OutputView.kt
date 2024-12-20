package com.mvc.view

import com.mvc.model.dto.CarResponse

interface OutputView {

    fun printWinners(winners: List<CarResponse>)
}
