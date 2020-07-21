package vm.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DisplayTest : StringSpec({

    "empty display" {
        val emptyDisplay = Display()

        emptyDisplay.text shouldBe "INSERT COIN"
        emptyDisplay.texts(3) shouldBe listOf("INSERT COIN", "INSERT COIN", "INSERT COIN")
    }

    "display with values" {
        val display = Display(firstText = "first", defaultText = "default")

        display.text shouldBe "first"
        display.texts(3) shouldBe listOf("first", "default", "default")
    }

})