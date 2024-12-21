package hexagonal.adapter.`in`.console.view

interface InputView {

    fun requestCarNames(): List<String>

    fun requestTryCount(): Int
}
