package vm.validation

class ValidationException(message: String) : RuntimeException(message)

sealed class Validation
data class IntValidation(val value: Int) : Validation() {

    fun isGreaterOrEqual(limit: Int) {
        if (!(value >= limit)) {
            throw ValidationException("value $value must be greater or equal $limit but is not")
        }
    }

    fun isGreaterThan(limit: Int) {
        if (!(value > limit)) {
            throw ValidationException("value $value must be greater than $limit but is not")
        }
    }
}

fun validate(value: Int): IntValidation =
    IntValidation(value)