package hexagonal.domain

import hexagonal.application.service.`in`.FakeFuelPositiveGenerator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CarsTest : BehaviorSpec({

    val fuelPositiveGenerator = FakeFuelPositiveGenerator()

    given("getWinner : 우승자를 구할 때") {

        `when`("우승자가 여러 명일 때") {
            val cars = Cars.from(listOf("jetty", "millo"))
            cars.startRace(fuelPositiveGenerator)
            val winners = cars.getWinners()

            then("모두 찾아서 반환한다") {
                winners.size shouldBe 2
            }
        }
    }
})
