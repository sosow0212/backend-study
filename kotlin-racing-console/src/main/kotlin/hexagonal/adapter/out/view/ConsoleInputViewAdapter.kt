package hexagonal.adapter.out.view

import hexagonal.adapter.`in`.console.view.InputView

class ConsoleInputViewAdapter : InputView {

    override fun requestCarNames(): List<String> {
        println("---hexagonal ver---")
        println("차량의 이름을 등록해주세요. (ex. jetty, crong, popo)")
        return readln().split(",")
            .map { it.trim() }
    }

    override fun requestTryCount(): Int {
        println("경주 횟수를 입력해주세요.")
        return readln().toInt()
    }
}
