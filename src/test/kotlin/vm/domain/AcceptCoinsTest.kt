package vm.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class AcceptCoinsTest {

    val invalidCoin = Coin(Weight(1), Size(1), Size(1))

    @Test
    fun `accept coins`() {
        val ventingMachine = VentingMachine()
            .accept(Nickel.coin)
            .accept(invalidCoin)
            .accept(Dime.coin)
            .accept(Quarter.coin)
            .accept(invalidCoin)
            .accept(Dime.coin)

        assertThat(ventingMachine.coins)
            .containsExactlyInAnyOrder(Nickel, Dime, Dime, Quarter)

        assertThat(ventingMachine.coinReturn)
            .containsExactlyInAnyOrder(RejectedCoin(invalidCoin), RejectedCoin(invalidCoin))

        assertThat(ventingMachine.amount)
            .isEqualTo(Nickel.value + 2 * Dime.value + Quarter.value)

        assertThat(ventingMachine.display)
            .isEqualTo("${ventingMachine.amount} CENT")
    }

}