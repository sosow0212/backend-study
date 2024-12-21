package mvc.model.vo

import com.mvc.model.vo.MoveCount
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MoveCountTest : BehaviorSpec({

    Given("move: 움직일 때") {
        When("연료가 5이상이면") {
            val moveCount = MoveCount.createDefault()  // 각 테스트마다 새로운 객체 생성
            val startedValue = moveCount.value

            moveCount.move(10)

            Then("움직인다") {
                moveCount.value shouldBe startedValue + 1
            }
        }

        When("연료가 5미만이면") {
            val moveCount = MoveCount.createDefault()
            val startedValue = moveCount.value

            moveCount.move(1)

            Then("움직이지 않는다") {
                moveCount.value shouldBe startedValue
            }
        }
    }
})
