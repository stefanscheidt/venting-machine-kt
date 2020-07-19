package vm.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe


class AcceptCoinsTest : StringSpec({

    "accept coins" {
        val invalidCoin = coinOf(weight = 1, diameter = 1, thickness = 1)

        val coins = listOf(Nickel.coin, invalidCoin, Dime.coin, Quarter.coin, invalidCoin, Dime.coin)
        val ventingMachine = coins.fold(VentingMachine()) { machine, coin -> machine.accept(coin) }

        ventingMachine.coins.shouldContainExactlyInAnyOrder(Nickel, Dime, Dime, Quarter)

        ventingMachine.coinReturn.shouldContainExactlyInAnyOrder(RejectedCoin(invalidCoin), RejectedCoin(invalidCoin))

        ventingMachine.amount shouldBe (Nickel.value + Dime.value + Quarter.value + Dime.value)

        ventingMachine.display shouldBe "${ventingMachine.amount} CENT"
    }

})