package vm.domain


data class VentingMachine(
    val coins: List<ValidCoin> = emptyList(),
    val coinReturn: List<ValidatedCoin> = emptyList()
) {
    val amount: Int
        get() = coins.sumBy { it.value }
    val display: String
        get() = when {
            coins.isEmpty() -> "INSERT COIN"
            else -> "$amount CENT"
        }
}

fun VentingMachine.accept(coin: Coin): VentingMachine =
    when (val validatedCoin = coin.validate()) {
        is ValidCoin -> this.copy(coins = coins.plus(validatedCoin))
        is RejectedCoin -> this.copy(coinReturn = coinReturn.plus(validatedCoin))
    }
