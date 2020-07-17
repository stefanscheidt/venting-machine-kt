package vm.domain

import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class VentingMachineTest : StringSpec({

    "empty machine waits for coin" {
        val emptyMachine = VentingMachine()

        emptyMachine.coins.shouldBeEmpty()
        emptyMachine.coinReturn.shouldBeEmpty()
        emptyMachine.amount.shouldBe(0)
        emptyMachine.display.shouldBe("INSERT COIN")
    }

})