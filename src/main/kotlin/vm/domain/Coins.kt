package vm.domain

import vm.validation.validate

/** weight unit is milligram */
data class Weight(
    val value: Int
) {
    init {
        validate(value).isGreaterThan(0)
    }
}

/** size unit is micrometer */
data class Size(
    val value: Int
) {
    init {
        validate(value).isGreaterThan(0)
    }
}

data class Coin(
    val weight: Weight,
    val diameter: Size,
    val thickness: Size
)

fun coinOf(weight: Int, diameter: Int, thickness: Int): Coin =
    Coin(Weight(weight), Size(diameter), Size(thickness))