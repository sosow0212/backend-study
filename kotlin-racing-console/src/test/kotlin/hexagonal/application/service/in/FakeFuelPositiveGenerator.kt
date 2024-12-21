package hexagonal.application.service.`in`

import hexagonal.domain.service.FuelGenerator

class FakeFuelPositiveGenerator : FuelGenerator {

    override fun generate(): Int {
        return 10
    }
}
