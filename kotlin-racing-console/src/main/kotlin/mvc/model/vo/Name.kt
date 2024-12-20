package com.mvc.model.vo

import com.mvc.model.exception.NameLengthInvalidException

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
