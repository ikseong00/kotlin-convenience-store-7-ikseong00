package store.service

import store.model.PurchaseProduct
import store.model.Stock
import store.utils.ExtensionUtil.toProduct
import store.utils.Validator
import store.view.InputView

object InputService {

    fun getPurchaseInfoToProducts(stocks: List<Stock>): List<PurchaseProduct> {
        while (true) {
            try {
                val separatedInfo = getAndSeparateInput()
                return separatedInfoToProducts(separatedInfo, stocks)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                continue
            }
        }
    }

    private fun separatedInfoToProducts(
        separatedInfo: List<String>,
        stocks: List<Stock>
    ): List<PurchaseProduct> {
        return separatedInfo.map {
            val (name, quantity) = it.substring(1, it.length - 1).split("-")
            Validator.validateProductPurchasable(name, quantity.toInt(), stocks)
            stocks.find { stock -> stock.name == name }!!
                .toProduct()
        }.toList()
    }

    private fun getAndSeparateInput(): List<String> {
        val input = InputView.getPurchaseInfo()
        return Validator.validatePurchaseInfo(input)
    }

    fun getPromotionQuantityAddition(productName: String): String {
        while (true) {
            try {
                val input = InputView.getPromotionAddition(productName)
                return Validator.validateYesOrNo(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                continue
            }
        }
    }

    fun getDefaultPricePurchase(productName: String, productQuantity: Int): String {
        while (true) {
            try {
                val input = InputView.getNoneDiscountPromotion(productName, productQuantity)
                return Validator.validateYesOrNo(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                continue
            }
        }
    }

    fun getMembershipDiscount(): String {
        while (true) {
            try {
                val input = InputView.getMembership()
                return Validator.validateYesOrNo(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                continue
            }
        }
    }

    fun getRepurchase(): String {
        while (true) {
            try {
                val input = InputView.getRepurchase()
                return Validator.validateYesOrNo(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                continue
            }
        }
    }

}