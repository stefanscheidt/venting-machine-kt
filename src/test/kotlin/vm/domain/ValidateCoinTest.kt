package vm.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValidateCoinTest {

    @Test
    fun `validate valid coin`() {
        assertThat(Nickel.coin.validate()).isEqualTo(Nickel)
        assertThat(Dime.coin.validate()).isEqualTo(Dime)
        assertThat(Quarter.coin.validate()).isEqualTo(Quarter)
    }

    @Test
    fun `validate invalid coin`() {
        val invalidCoin = Coin(Weight(1), Size(1), Size(1))
        assertThat(invalidCoin.validate()).isEqualTo(RejectedCoin(invalidCoin))
    }
}