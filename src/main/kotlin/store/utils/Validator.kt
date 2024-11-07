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
        val stockNames = stocks.map { it.name }.toList()

        checkProductName(inputProductName, stockNames)
        checkProductQuantity(inputProductName, inputProductQuantity, stocks)

        return true
    }

    private fun checkProductName(inputProductName: String, productStocks: List<String>) {
        if (!productStocks.contains(inputProductName)) {
            throw IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.")
        }
    }

    private fun checkProductQuantity(
        inputProductName: String,
        inputProductQuantity: Int,
        stocks: List<StockEntity>
    ) {
        val stock = stocks.find { it.name == inputProductName }!!
        if (stock.quantity < inputProductQuantity) {
            throw IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.")
        }
    }


}