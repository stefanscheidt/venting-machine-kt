package vm.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ValidateCoinTest : StringSpec({

    "validate valid coin" {
        Nickel.coin.validated() shouldBe Nickel
        Dime.coin.validated() shouldBe Dime
        Quarter.coin.validated() shouldBe Quarter
    }

    "validate invalid coin" {
        val invalidCoin = coinOf(weight = 1, diameter = 1, thickness = 1)

        invalidCoin.validated() shouldBe RejectedCoin(invalidCoin)
    }

})
