package com.example.sync.version.domain.vo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import java.util.*

enum class OsType(
    private val value: String
) {

    ANDROID("android"),
    IOS("ios");

    @JsonValue
    fun toValue(): String = this.value

    companion object {
        @JsonCreator
        @JvmStatic
        fun from(value: String): OsType {
            return entries.find { it.value.equals(value, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown os type: $value")
        }
    }
}
