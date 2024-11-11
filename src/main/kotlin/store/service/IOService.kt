package store.service

import store.model.PurchaseProduct
import store.model.Stock
import store.model.UserAnswer
import store.utils.ExtensionUtil.toProduct
import store.utils.Validator
import store.view.InputView
import store.view.OutputView

object IOService {

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
                .toProduct(quantity.toInt())
        }.toList()
    }

    private fun getAndSeparateInput(): List<String> {
        OutputView.printPurchaseMessage()
        val input = InputView.getPurchaseInfo()
        return Validator.validatePurchaseInfo(input)
    }

    fun getPromotionQuantityAddition(productName: String): UserAnswer {
        while (true) {
            try {
                OutputView.printPromotionAddition(productName)
                val input = InputView.getPromotionAddition(productName)
                return Validator.validateYesOrNo(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                continue
            }
        }
    }

    fun getDefaultPricePurchase(productName: String, productQuantity: Int): UserAnswer {
        while (true) {
            try {
                OutputView.getNoneDiscountPromotion(productName, productQuantity)
                val input = InputView.getNoneDiscountPromotion(productName, productQuantity)
                return Validator.validateYesOrNo(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                continue
            }
        }
    }

    fun getMembershipDiscount(): UserAnswer {
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

    fun getRepurchase(): UserAnswer {
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