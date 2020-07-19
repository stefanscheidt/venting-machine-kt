package vm.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe


class VentingMachineTest : StringSpec({

    "empty machine waits for coin" {
        val emptyMachine = VentingMachine()

        emptyMachine.coins.shouldBeEmpty()
        emptyMachine.coinReturn.shouldBeEmpty()
        emptyMachine.amount.shouldBe(0)
        emptyMachine.display.shouldBe("INSERT COIN")
    }

})