package com.study.post.domain.model.vo

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class TitleTest {

    @Test
    fun `금칙어가_포함되면_예외를_던진다`() {
        // given
        val value: String = "금칙어1"

        // when & then
        assertThatThrownBy {
            Title.from(value)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }
}
