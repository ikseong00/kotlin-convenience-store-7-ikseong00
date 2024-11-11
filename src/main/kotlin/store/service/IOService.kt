package store.service

import store.model.PurchaseProduct
import store.model.Receipt
import store.model.Stock
import store.model.UserAnswer
import store.utils.Validator
import store.view.InputView
import store.view.OutputView

object IOService {

    fun getPurchaseInfoToProducts(stocks: List<Stock>): List<PurchaseProduct> {
        printInitMessage(stocks)
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

    private fun printInitMessage(stocks: List<Stock>) {
        OutputView.printWelcomeMessage()
        OutputView.printStocks(stocks)
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
        val input = InputView.getUserInput()
        return Validator.validatePurchaseInfo(input)
    }


    fun printReceipt(receipt: Receipt, purchaseProducts: List<PurchaseProduct>) {
        OutputView.printProductReceipt(purchaseProducts)
        OutputView.printPromotionReceipt(
            purchaseProducts.filter { it.isPromotion }
        )
        OutputView.printTotalMoneyReceipt(receipt)
    }

    fun getPromotionQuantityAddition(productName: String): UserAnswer {
        while (true) {
            try {
                OutputView.printPromotionAddition(productName)
                val input = InputView.getUserInput()
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
                val input = InputView.getUserInput()
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
                OutputView.printMembership()
                val input = InputView.getUserInput()
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
                OutputView.printRepurchase()
                val input = InputView.getUserInput()
                return Validator.validateYesOrNo(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                continue
            }
        }
    }

}