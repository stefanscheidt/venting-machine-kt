package vm.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe


class VentingMachineTest : StringSpec({

    "empty machine in empty and waits for coin" {
        with(VentingMachine()) {
            coins.shouldBeEmpty()
            coinReturn.shouldBeEmpty()
            amount shouldBe 0
            display.text shouldBe "INSERT COIN"
        }
    }

})