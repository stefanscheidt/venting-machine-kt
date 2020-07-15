package vm.domain

import vm.validation.validate

sealed class ValidatedCoin

/** valid coin value unit is cent */
sealed class ValidCoin(val value: Int, val coin: Coin) : ValidatedCoin() {
    init {
        validate(value).isGreaterOrEqual(0)
    }
}

// https://en.wikipedia.org/wiki/Nickel_(United_States_coin)
object Nickel : ValidCoin(5, Coin(weight = Weight(5000), diameter = Size(21210), thickness = Size(1950)))

// https://en.wikipedia.org/wiki/Dime_(United_States_coin)
object Dime : ValidCoin(10, Coin(weight = Weight(2268), diameter = Size(17910), thickness = Size(1350)))

// https://en.wikipedia.org/wiki/Quarter_(United_States_coin)
object Quarter : ValidCoin(25, Coin(weight = Weight(5670), diameter = Size(24260), thickness = Size(1750)))

data class RejectedCoin(val coin: Coin) : ValidatedCoin()

fun Coin.validate(): ValidatedCoin =
    when {
        this == Nickel.coin -> Nickel
        this == Dime.coin -> Dime
        this == Quarter.coin -> Quarter
        else -> RejectedCoin(this)
    }
