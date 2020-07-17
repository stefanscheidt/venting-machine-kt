package vm.domain

import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec


class AcceptCoinsTest : StringSpec({

    "accept coins" {
        val invalidCoin = coinOf(weight = 1, diameter = 1, thickness = 1)

        val ventingMachine = VentingMachine()
            .accept(Nickel.coin)
            .accept(invalidCoin)
            .accept(Dime.coin)
            .accept(Quarter.coin)
            .accept(invalidCoin)
            .accept(Dime.coin)

        ventingMachine.coins.shouldContainExactlyInAnyOrder(Nickel, Dime, Dime, Quarter)

        ventingMachine.coinReturn.shouldContainExactlyInAnyOrder(RejectedCoin(invalidCoin), RejectedCoin(invalidCoin))

        ventingMachine.amount.shouldBe(Nickel.value + 2 * Dime.value + Quarter.value)

        ventingMachine.display.shouldBe("${ventingMachine.amount} CENT")
    }

})