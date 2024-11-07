package store.utils

import store.model.StockEntity
import store.utils.message.ErrorMessages.MONEY_INPUT_FORMAT_ERROR
import store.utils.message.ErrorMessages.PRODUCT_NOT_FOUND_ERROR
import store.utils.message.ErrorMessages.STOCK_QUANTITY_ERROR
import store.utils.message.ErrorMessages.WRONG_INPUT_ERROR

object Validator {

    private const val REGEX =
        "(\\[(.[^]\\[\\-,]+)-(\\d+)])((,)(\\[(.[^]\\[\\-,]+)-(\\d+)]))*"
    const val YES = "Y"
    const val NO = "N"

    fun validatePurchaseInfo(inputPurchaseInfo: String): List<String> {
        val pattern = Regex(REGEX)
        when {
            inputPurchaseInfo.isBlank() -> throw IllegalArgumentException(MONEY_INPUT_FORMAT_ERROR)
            !pattern.matches(inputPurchaseInfo) -> throw IllegalArgumentException(
                MONEY_INPUT_FORMAT_ERROR
            )
        }
        return inputPurchaseInfo.split(",")
    }


    fun validateProductPurchasable(
        inputProductName: String,
        inputProductQuantity: Int,
        stocks: List<StockEntity>
    ): Boolean {
        val stockNames = stocks.map { it.name }.toList()

        checkProductName(inputProductName, stockNames)
        checkProductQuantity(inputProductName, inputProductQuantity, stocks)

        return true
    }

    private fun checkProductName(inputProductName: String, productStocks: List<String>) {
        if (!productStocks.contains(inputProductName)) {
            throw IllegalArgumentException(PRODUCT_NOT_FOUND_ERROR)
        }
    }

    private fun checkProductQuantity(
        inputProductName: String,
        inputProductQuantity: Int,
        stocks: List<StockEntity>
    ) {
        val stock = stocks.find { it.name == inputProductName }!!
        if (stock.quantity < inputProductQuantity) {
            throw IllegalArgumentException(STOCK_QUANTITY_ERROR)
        }
    }

    fun validateYesOrNo(input: String): String {
        when (input) {
            YES, NO -> return input
        }
        throw IllegalArgumentException(WRONG_INPUT_ERROR)
    }


}