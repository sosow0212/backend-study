package mvc.model.vo

import com.mvc.model.exception.NameLengthInvalidException
import com.mvc.model.vo.Name
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class NameTest : BehaviorSpec({

    Given("constructor: 이름을 생성할 때") {
        val successCaseName = "jetty"
        val failCaseName = "a"

        When("길이가 3이상이면") {
            val name = Name(successCaseName)

            Then("정상 생성된다") {
                name.value shouldBe successCaseName
            }
        }

        When("길이가 3미만이면") {
            Then("예외를 발생한다") {
                shouldThrow<NameLengthInvalidException> {
                    Name(failCaseName)
                }
            }
        }
    }
})
