package vm.domain

import java.text.NumberFormat
import java.util.*


data class VentingMachine(
    val coins: List<ValidCoin> = emptyList(),
    val coinReturn: List<ValidatedCoin> = emptyList(),
    val display: Display = Display()
) {
    val amount: Int
        get() = coins.sumBy { it.value }
}

fun VentingMachine.insert(coin: Coin): VentingMachine =
    when (val validatedCoin = coin.validate()) {
        is ValidCoin -> copy(
            coins = coins.plus(validatedCoin),
            display = Display(defaultText = (amount + validatedCoin.value).currencyString)
        )
        is RejectedCoin -> copy(
            coinReturn = coinReturn.plus(validatedCoin)
        )
    }

fun VentingMachine.selectProduct(product: Product): Pair<Product?, VentingMachine> =
    if (amount >= product.value) {
        val nextDisplay = Display(firstText = "THANK YOU")
        val nextMachine = copy(coins = emptyList(), display = nextDisplay)
        Pair(product, nextMachine)
    } else {
        val nextDisplay = display.copy(firstText = "PRICE ${product.value.currencyString}")
        val nextMachine = copy(display = nextDisplay)
        Pair(null, nextMachine)
    }

data class Display(
    private val firstText: String? = null,
    private val defaultText: String = "INSERT COIN"
) {
    val text: String =
        firstText ?: defaultText

    fun texts(n: Int): List<String> =
        generateSequence(text) { defaultText }
            .take(n)
            .toList()
}

private val currencyFormat: NumberFormat =
    NumberFormat.getCurrencyInstance(Locale.US)

internal val Int.currencyString: String
    get() = currencyFormat.format(this / 100.0)