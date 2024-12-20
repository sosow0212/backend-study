package com.view.console

import com.view.InputView

class ConsoleInputView : InputView {

    override fun requestCarNames(): List<String> {
        print("차량의 이름을 등록해주세요. (ex. jetty, crong, popo)")
        return readln().split(",")
            .map { it.trim() }
    }

    override fun requestTryCount(): Int {
        print("경주 횟수를 입력해주세요.")
        return readln().toInt()
    }
}
