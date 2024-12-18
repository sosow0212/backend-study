package com.study.post.domain.model.vo

@JvmInline
value class Title private constructor(val value: String) {

    init {
        validateTitle(value)
    }

    private fun validateTitle(value: String) {
        require(!BANNED_TITLE_CONTAINS_WORD.any { word -> value.contains(word) }) { "금칙어가 포함되었습니다." }
    }

    companion object {
        private val BANNED_TITLE_CONTAINS_WORD = listOf("금칙어1", "금칙어2")

        fun from(title: String): Title {
            return Title(title)
        }
    }
}
