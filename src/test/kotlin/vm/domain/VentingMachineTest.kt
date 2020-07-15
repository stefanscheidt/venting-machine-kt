package vm.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VentingMachineTest {

    @Test
    fun `empty machine`() {
        val emptyMachine = VentingMachine()

        assertThat(emptyMachine.coins).isEmpty()
        assertThat(emptyMachine.coinReturn).isEmpty()
        assertThat(emptyMachine.amount).isEqualTo(0)
        assertThat(emptyMachine.display).isEqualTo("INSERT COIN")
    }

}