package vm.domain

import vm.validation.validate

sealed class ValidatedCoin

/** valid coin value unit is cent */
sealed class ValidCoin(val value: Int, val coin: Coin) : ValidatedCoin() {
    init {
        validate(value).isGreaterThan(0)
    }
}

// https://en.wikipedia.org/wiki/Nickel_(United_States_coin)
object Nickel : ValidCoin(5, coinOf(weight = 5000, diameter = 21210, thickness = 1950))

// https://en.wikipedia.org/wiki/Dime_(United_States_coin)
object Dime : ValidCoin(10, coinOf(weight = 2268, diameter = 17910, thickness = 1350))

// https://en.wikipedia.org/wiki/Quarter_(United_States_coin)
object Quarter : ValidCoin(25, coinOf(weight = 5670, diameter = 24260, thickness = 1750))

data class RejectedCoin(val coin: Coin) : ValidatedCoin()

fun Coin.matches(validCoin: ValidCoin): Boolean =
    this == validCoin.coin

fun Coin.validated(): ValidatedCoin =
    when {
        this.matches(Nickel) -> Nickel
        this.matches(Dime) -> Dime
        this.matches(Quarter) -> Quarter
        else -> RejectedCoin(this)
    }
