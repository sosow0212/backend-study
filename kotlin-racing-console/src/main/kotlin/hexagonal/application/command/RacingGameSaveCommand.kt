package hexagonal.application.command

data class RacingGameSaveCommand(
    val names: List<String>,
    val tryCount: Int
)
