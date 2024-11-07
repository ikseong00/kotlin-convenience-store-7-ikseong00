package store.utils

import store.utils.message.ErrorMessages.MONEY_BLANK_ERROR
import store.utils.message.ErrorMessages.MONEY_INPUT_FORMAT_ERROR

object Validator {

    private const val REGEX =
        "(\\[(.[^]\\[\\-,]+)-(\\d+)])((,)(\\[(.[^]\\[\\-,]+)-(\\d+)]))*"

    fun validatePurchaseInfo(inputPurchaseInfo: String): List<String> {

        val pattern = Regex(REGEX)
        when {
            inputPurchaseInfo.isBlank() -> throw IllegalArgumentException(MONEY_BLANK_ERROR)
            !pattern.matches(inputPurchaseInfo) -> throw IllegalArgumentException(MONEY_INPUT_FORMAT_ERROR)
            else -> return inputPurchaseInfo.split(",")
        }

    }

}