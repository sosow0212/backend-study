package mvc.model.vo

import com.mvc.model.vo.MoveCount
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MoveCountTest : BehaviorSpec({

    given("move: 움직일 때") {
        `when`("연료가 5이상이면") {
            val moveCount = MoveCount.createDefault()  // 각 테스트마다 새로운 객체 생성
            val startedValue = moveCount.value

            moveCount.move(10)

            then("움직인다") {
                moveCount.value shouldBe startedValue + 1
            }
        }

        `when`("연료가 5미만이면") {
            val moveCount = MoveCount.createDefault()
            val startedValue = moveCount.value

            moveCount.move(1)

            then("움직이지 않는다") {
                moveCount.value shouldBe startedValue
            }
        }
    }
})
