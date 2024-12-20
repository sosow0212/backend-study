package com.model.exception

class TryCountInvalidExecution : RuntimeException(
    "시도 횟수는 0보다 커야합니다."
) {
}
