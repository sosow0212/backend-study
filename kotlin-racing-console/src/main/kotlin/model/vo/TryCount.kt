package com.model.vo

import com.model.exception.TryCountInvalidExecution

@JvmInline
value class TryCount(
    val value: Int
) {

    init {
        require(value >= MINIMUM_TRY_COUNT) { TryCountInvalidExecution() }
    }

    companion object {
        private const val MINIMUM_TRY_COUNT = 1;
    }
}
