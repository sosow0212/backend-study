package model.vo

import com.model.exception.NameLengthInvalidException
import com.model.vo.Name
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class NameTest : BehaviorSpec({

    given("constructor: 이름을 생성할 때") {
        val successCaseName = "jetty"
        val failCaseName = "a"

        `when`("길이가 3이상이면") {
            val name = Name(successCaseName)

            then("정상 생성된다") {
                name.value shouldBe successCaseName
            }
        }

        `when`("길이가 3미만이면") {
            then("예외를 발생한다") {
                shouldThrow<NameLengthInvalidException> {
                    Name(failCaseName)
                }
            }
        }
    }
})
