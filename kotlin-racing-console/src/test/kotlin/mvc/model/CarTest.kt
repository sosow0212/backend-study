package com.model

import com.mvc.model.Car
import com.mvc.model.exception.NameLengthInvalidException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CarTest : BehaviorSpec({

    given("constructor: 차량을 생성하는데 이름이") {
        `when`("3자 이상이면") {
            then("정상 생성된다.") {
                val car = Car.from("jetty")
                car.name.value shouldBe "jetty"
            }
        }

        `when`("3자 미만이면") {
            then("예외를 발생시킨다.") {
                shouldThrow<NameLengthInvalidException> {
                    Car.from("A")
                }.message shouldBe NameLengthInvalidException().message
            }
        }
    }
})
