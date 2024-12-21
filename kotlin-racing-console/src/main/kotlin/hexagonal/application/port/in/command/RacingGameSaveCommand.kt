package hexagonal.application.port.`in`.command

data class RacingGameSaveCommand(
    val names: List<String>,
    val tryCount: Int
)
