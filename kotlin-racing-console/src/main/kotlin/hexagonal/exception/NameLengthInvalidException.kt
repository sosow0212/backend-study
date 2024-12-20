package com.hexagonal.model.exception

class NameLengthInvalidException : RuntimeException(
    "차량 이름의 길이는 3자 이상이어야 합니다."
) {
}
