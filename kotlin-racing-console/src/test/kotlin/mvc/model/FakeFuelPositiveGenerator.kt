package mvc.model

import com.mvc.model.FuelGenerator

class FakeFuelPositiveGenerator : FuelGenerator {

    override fun generate(): Int {
        return 10
    }
}
