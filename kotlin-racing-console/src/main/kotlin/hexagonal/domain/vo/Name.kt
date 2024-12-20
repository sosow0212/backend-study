package com.hexagonal.model.vo

import com.hexagonal.model.exception.NameLengthInvalidException

@JvmInline
value class Name(
    val value: String
) {

    init {
        if (value.length < MINIMUM_NAME_LENGTH) {
            throw NameLengthInvalidException()
        }
    }

    companion object {
        private const val MINIMUM_NAME_LENGTH = 3
    }
}
