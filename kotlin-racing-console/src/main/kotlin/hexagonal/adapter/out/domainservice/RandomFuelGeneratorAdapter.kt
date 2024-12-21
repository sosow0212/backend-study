package hexagonal.adapter.out.domainservice

import hexagonal.domain.service.FuelGenerator

class RandomFuelGeneratorAdapter : FuelGenerator {

    override fun generate(): Int {
        return (MIN_FUEL..MAX_FUEL).random()
    }

    companion object {
        private const val MIN_FUEL = 1
        private const val MAX_FUEL = 10
    }
}
