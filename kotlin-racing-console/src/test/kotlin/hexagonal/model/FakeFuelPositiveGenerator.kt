package hexagonal.model

import com.hexagonal.model.FuelGenerator

class FakeFuelPositiveGenerator : FuelGenerator {

    override fun generate(): Int {
        return 10
    }
}
