package com.study.post.application.service

import com.study.post.application.port.`in`.command.PostCreateCommand
import com.study.post.application.port.`in`.command.PostUpdateCommand
import com.study.post.application.port.out.FakePostRepository
import com.study.post.domain.model.Post
import com.study.post.exception.PostException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.function.Executable

class PostServiceTest {

    private val fakePostRepository = FakePostRepository()
    private val postService = PostService(fakePostRepository)

    @Nested
    inner class CreatePostTest {

        @Test
        fun `정상적으로 게시글이 생성된다`() {
            // given
            val command = PostCreateCommand("title", "content")

            // when & then
            assertDoesNotThrow { postService.create(command) }
        }

        @Test
        fun `금칙어가 포함되면 예외를 반환한다`() {
            // given
            val bannedTitle = "금칙어1"

            // when & then
            assertThatThrownBy {
                postService.create(PostCreateCommand(bannedTitle, "content"))
            }.isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Nested
    inner class UpdatePostTest {

        @Test
        fun `정상적으로 게시글이 업데이트 된다`() {
            // given
            val savedPost = fakePostRepository.save(Post.of(title = "title", content = "content"))
            val command = PostUpdateCommand("edit", "edit")

            // when
            val result = savedPost.id?.let { postService.update(id = it, postUpdateCommand = command) }

            // then
            assertAll(
                Executable { Assertions.assertNotNull(result) },
                Executable { assertEquals(result!!.title, command.title) },
                Executable { assertEquals(result!!.content, command.content) },
            )
        }

        @Test
        fun `업데이트 과정에서 게시글이 없다면 예외를 발생시킨다`() {
            // given
            val command = PostUpdateCommand("edit", "edit")

            // when & then
            assertThatThrownBy {
                postService.update(0L, command)
            }.isInstanceOf(PostException::class.java)
        }

        @Test
        fun `금칙어가 포함되면 예외를 반환한다`() {
            // given
            val savedPost = fakePostRepository.save(Post.of(title = "title", content = "content"))
            val command = PostUpdateCommand("금칙어1", "edit")

            // when & then
            assertThatThrownBy {
                postService.update(savedPost.id!!, command)
            }.isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Test
    fun `조회시 id를 찾을 수 없다면 예외를 발생한다`() {
        // when & then
        assertThatThrownBy {
            postService.findById(0L)
        }.isInstanceOf(PostException::class.java)
    }
}
