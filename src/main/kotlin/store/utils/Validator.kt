package store.utils

import store.model.Stock
import store.model.UserAnswer
import store.utils.message.Constants.COMMA
import store.utils.message.Constants.NO
import store.utils.message.Constants.REGEX
import store.utils.message.Constants.YES
import store.utils.message.ErrorMessages.MONEY_INPUT_FORMAT_ERROR
import store.utils.message.ErrorMessages.PRODUCT_NOT_FOUND_ERROR
import store.utils.message.ErrorMessages.STOCK_QUANTITY_ERROR
import store.utils.message.ErrorMessages.WRONG_INPUT_ERROR

object Validator {

    fun validatePurchaseInfo(inputPurchaseInfo: String): List<String> {
        when {
            inputPurchaseInfo.isBlank() ->
                throw IllegalArgumentException(MONEY_INPUT_FORMAT_ERROR)

            !Regex(REGEX).matches(inputPurchaseInfo) ->
                throw IllegalArgumentException(MONEY_INPUT_FORMAT_ERROR)
        }
        return inputPurchaseInfo.split(COMMA)
    }

    fun validateProductPurchasable(
        inputProductName: String,
        inputProductQuantity: Int,
        stocks: List<Stock>
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
        stocks: List<Stock>
    ) {
        val stock = stocks.find { it.name == inputProductName }!!
        val totalQuantity = stock.quantity + stock.promotionQuantity
        if (totalQuantity < inputProductQuantity) {
            throw IllegalArgumentException(STOCK_QUANTITY_ERROR)
        }
    }

    fun validateYesOrNo(input: String): UserAnswer {
        when (input) {
            YES -> return UserAnswer.YES
            NO -> return UserAnswer.NO
        }
        throw IllegalArgumentException(WRONG_INPUT_ERROR)
    }


}