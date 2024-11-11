package store.view

import store.model.PurchaseProduct
import store.model.Receipt
import store.model.Stock
import store.utils.message.OutputMessages.DIVIDING_LINE_MESSAGE
import store.utils.message.OutputMessages.MEMBERSHIP_DISCOUNT_INPUT_MESSAGE
import store.utils.message.OutputMessages.NOW
import store.utils.message.OutputMessages.PRICE_INTRODUCTION_INPUT_MESSAGE
import store.utils.message.OutputMessages.PRODUCT_RECEIPT_MESSAGE
import store.utils.message.OutputMessages.PROMOTION_INTRODUCTION_INPUT_MESSAGE
import store.utils.message.OutputMessages.PROMOTION_RECEIPT_MESSAGE
import store.utils.message.OutputMessages.PURCHASE_INPUT_MESSAGE
import store.utils.message.OutputMessages.REPURCHASE_INPUT_MESSAGE
import store.utils.message.OutputMessages.WELCOME_MESSAGE
import store.utils.message.OutputMessages.W_CONVENIENCE_STORE

object OutputView {
    fun printWelcomeMessage() {
        println(WELCOME_MESSAGE)
    }

    fun printStocks(stocks: List<Stock>) {
        stocks.forEach {
            it.printStock()
        }
        println()
    }

    fun printPurchaseMessage() {
        println(PURCHASE_INPUT_MESSAGE)
    }

    fun printPromotionAddition(inputProductName: String) {
        println(NOW + inputProductName + PROMOTION_INTRODUCTION_INPUT_MESSAGE)
    }

    fun getNoneDiscountPromotion(inputProductName: String, inputProductQuantity: Int) {
        println("$NOW$inputProductName $inputProductQuantity$PRICE_INTRODUCTION_INPUT_MESSAGE")
    }

    fun printMembership() {
        println(MEMBERSHIP_DISCOUNT_INPUT_MESSAGE)
    }

    fun printProductReceipt(purchaseProducts: List<PurchaseProduct>) {
        println(W_CONVENIENCE_STORE)
        println(PRODUCT_RECEIPT_MESSAGE)
        purchaseProducts.forEach {
            it.printQuantityAndPrice()
        }
    }

    fun printPromotionReceipt(purchaseProducts: List<PurchaseProduct>) {
        println(PROMOTION_RECEIPT_MESSAGE)
        purchaseProducts.forEach {
            it.printPresentedQuantity()
        }
    }

    fun printTotalMoneyReceipt(
        receipt: Receipt
    ) {
        println(DIVIDING_LINE_MESSAGE)

        println(receipt)
    }

    fun printRepurchase() {
        println(REPURCHASE_INPUT_MESSAGE)
    }


}