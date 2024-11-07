package store.service

import store.model.PurchaseProduct
import store.model.Stock
import store.utils.Validator
import store.view.InputView

class InputService(
    private val stocks: List<Stock>
) {

    fun getPurchaseInfoToProducts(): List<PurchaseProduct> {
        while (true) {
            try {
                val separatedInfo = separatePurchaseInput()
                return separatedInfoToProducts(separatedInfo)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                continue
            }
        }
    }

    private fun separatedInfoToProducts(separatedInfo: List<String>): List<PurchaseProduct> {
        return separatedInfo.map {
            val (name, quantity) = it.substring(1, it.length - 1).split("-")
            Validator.validateProductPurchasable(name, quantity.toInt(), stocks)
            PurchaseProduct(name, quantity.toInt())
        }.toList()
    }

    private fun separatePurchaseInput(): List<String> {
        val input = InputView.getPurchaseInfo()
        return Validator.validatePurchaseInfo(input)
    }

}