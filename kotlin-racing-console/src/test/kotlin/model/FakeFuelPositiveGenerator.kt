package model

import com.model.FuelGenerator

class FakeFuelPositiveGenerator : FuelGenerator {

    override fun generate(): Int {
        return 10
    }
}
