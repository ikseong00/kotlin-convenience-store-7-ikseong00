package store.utils

import store.model.StockEntity
import store.utils.message.ErrorMessages.MONEY_BLANK_ERROR
import store.utils.message.ErrorMessages.MONEY_INPUT_FORMAT_ERROR

object Validator {

    private const val REGEX =
        "(\\[(.[^]\\[\\-,]+)-(\\d+)])((,)(\\[(.[^]\\[\\-,]+)-(\\d+)]))*"

    fun validatePurchaseInfo(inputPurchaseInfo: String): List<String> {

        val pattern = Regex(REGEX)
        when {
            inputPurchaseInfo.isBlank() -> throw IllegalArgumentException(MONEY_BLANK_ERROR)
            !pattern.matches(inputPurchaseInfo) -> throw IllegalArgumentException(
                MONEY_INPUT_FORMAT_ERROR
            )

            else -> return inputPurchaseInfo.split(",")
        }

    }

    fun validateProductPurchasable(
        inputProductName: String,
        inputProductQuantity: Int,
        stocks: List<StockEntity>
    ): Boolean {
        // TODO : 1. 이름을 확인하고,2.  구매 가능한지 확인
        val stockNames = stocks.map { it.name }.toList()

        checkProductName(inputProductName, stockNames)

        return true
    }

    private fun checkProductName(inputProductName: String, productStocks: List<String>) {
        if (!productStocks.contains(inputProductName)) {
            throw IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.")
        }
    }

}