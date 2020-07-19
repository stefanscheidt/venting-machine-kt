package vm.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ValidateCoinTest : StringSpec({

    "validate valid coin" {
        Nickel.coin.validate() shouldBe Nickel
        Dime.coin.validate() shouldBe Dime
        Quarter.coin.validate() shouldBe Quarter
    }

    "validate invalid coin" {
        val invalidCoin = coinOf(weight = 1, diameter = 1, thickness = 1)

        invalidCoin.validate() shouldBe RejectedCoin(invalidCoin)
    }

})