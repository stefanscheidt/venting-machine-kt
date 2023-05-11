package vm.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import vm.domain.Product.CHIPS
import vm.domain.Product.COLA

/*
There are three products: cola for $1.00, chips for $0.50, and candy for $0.65.  When the respective button is pressed
and enough money has been inserted, the product is dispensed and the machine displays "THANK YOU".  If the display is
checked again, it will display INSERT COIN and the current amount will be set to $0.00. If there is not enough money
inserted then the machine displays PRICE and the price of the item and subsequent checks of the display will display
either INSERT COIN or the current amount as appropriate.
 */
class SelectProductTest : StringSpec({

    "insert nothing and select product" {
        val (product, machine) = VentingMachine()
            .selectProduct(COLA)

        product.shouldBeNull()
        machine.display.texts(2) shouldBe listOf("PRICE $1.00", "INSERT COIN")
    }

    "insert not enough and select product" {
        val (product, machine) = VentingMachine()
            .insert(Quarter.coin)
            .selectProduct(COLA)

        product.shouldBeNull()
        machine.display.texts(2) shouldBe listOf("PRICE $1.00", "$0.25")
    }

    "insert exact money and select product" {
        val (product, machine) = VentingMachine()
            .insert(Quarter.coin)
            .insert(Quarter.coin)
            .selectProduct(CHIPS)

        product shouldBe CHIPS
        machine.amount shouldBe 0
        machine.display.texts(2) shouldBe listOf("THANK YOU", "INSERT COIN")
    }

    "insert to much money and select product" {
        val (product, machine) = VentingMachine()
            .insert(Quarter.coin)
            .insert(Quarter.coin)
            .insert(Quarter.coin)
            .selectProduct(CHIPS)

        product shouldBe CHIPS
        machine.amount shouldBe 0
        machine.display.texts(2) shouldBe listOf("THANK YOU", "INSERT COIN")
    }

})
