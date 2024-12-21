package hexagonal.application.port.out.dto

data class CarOutboundResponse(
    val name: String,
    val moveCount: Int
)
