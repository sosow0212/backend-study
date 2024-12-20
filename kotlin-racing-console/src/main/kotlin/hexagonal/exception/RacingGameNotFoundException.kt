package hexagonal.exception

class RacingGameNotFoundException : RuntimeException(
    "게임 셋을 찾을 수 없습니다"
) {
}
