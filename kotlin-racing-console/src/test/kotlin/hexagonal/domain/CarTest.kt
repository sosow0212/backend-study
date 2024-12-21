package com.hexagonal

import com.hexagonal.model.Car
import com.hexagonal.model.exception.NameLengthInvalidException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CarTest : BehaviorSpec({

    Given("constructor: 차량을 생성하는데 이름이") {
        When("3자 이상이면") {
            Then("정상 생성된다.") {
                val car = Car.from("jetty")
                car.name.value shouldBe "jetty"
            }
        }

        When("3자 미만이면") {
            Then("예외를 발생시킨다.") {
                shouldThrow<NameLengthInvalidException> {
                    Car.from("A")
                }.message shouldBe NameLengthInvalidException().message
            }
        }
    }
})
