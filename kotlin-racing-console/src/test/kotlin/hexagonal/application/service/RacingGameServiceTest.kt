package hexagonal.application.service

import hexagonal.application.service.`in`.FakeCarRepository
import hexagonal.application.service.`in`.FakeFuelPositiveGenerator
import hexagonal.domain.RacingGame
import hexagonal.exception.RacingGameNotFoundException
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class RacingGameServiceTest : BehaviorSpec({

    lateinit var racingGameService: RacingGameService
    lateinit var carRepository: FakeCarRepository
    lateinit var fuelGenerator: FakeFuelPositiveGenerator

    beforeEach {
        carRepository = FakeCarRepository()
        fuelGenerator = FakeFuelPositiveGenerator()
        racingGameService = RacingGameService(
            carRepository = carRepository,
            fuelGenerator = fuelGenerator
        )
    }

    given("start: 게임을 실행할 때") {
        `when`("게임을 찾을 수 없다면") {
            then("예외가 발생한다") {
                shouldThrow<RacingGameNotFoundException> { racingGameService.start() }
            }
        }

        `when`("게임을 찾을 수 있다면") {
            val tryCount = 10

            then("진행 시킨다") {
                // 테스트 실행 전에 save 호출
                carRepository.save(RacingGame.of(listOf("aaaa", "bbbb"), tryCount))

                shouldNotThrow<RuntimeException> { racingGameService.start() }
            }
        }
    }

    given("findWinners: 우승자를 찾을 때") {
        `when`("게임을 찾을 수 없다면") {
            then("예외가 발생한다") {
                shouldThrow<RacingGameNotFoundException> { racingGameService.findWinners() }
            }
        }

        `when`("이동을 한다면") {
            then("우승자가 나온다") {
                carRepository.save(RacingGame.of(listOf("jetty"), 1))
                racingGameService.start()

                val result = racingGameService.findWinners()
                result.size shouldBe 1
                result[0].name shouldBe "jetty"
                result[0].moveCount shouldBe 1
            }
        }
    }
})

