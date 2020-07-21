package vm.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

/*
The vending machine will accept valid coins (nickels, dimes, and quarters) and reject invalid ones (pennies).  When a
valid coin is inserted the amount of the coin will be added to the current amount and the display will be updated.
When there are no coins inserted, the machine displays INSERT COIN.  Rejected coins are placed in the coin return.
 */
class InsertCoinsTest : StringSpec({

    "insert coins" {
        val coins = listOf(Nickel.coin, invalidCoin(), Dime.coin, Quarter.coin, invalidCoin(), Dime.coin)

        val ventingMachine = coins
            .fold(VentingMachine()) { machine, coin ->
                machine.insert(coin)
            }

        ventingMachine.coins.shouldContainExactlyInAnyOrder(Nickel, Dime, Dime, Quarter)

        ventingMachine.coinReturn.shouldContainExactlyInAnyOrder(
            RejectedCoin(invalidCoin()),
            RejectedCoin(invalidCoin())
        )

        ventingMachine.amount shouldBe 50

        ventingMachine.display.texts(2) shouldBe listOf("$0.50", "$0.50")
    }

})

private fun invalidCoin() = coinOf(weight = 1, diameter = 1, thickness = 1)