package store.view

import store.model.PurchaseProduct
import store.model.Receipt
import store.model.Stock
import store.utils.ExtensionUtil.toDecimalString
import store.utils.message.InputMessages.NOW
import store.utils.message.InputMessages.PROMOTION_INTRODUCTION_INPUT_MESSAGE
import store.utils.message.OutputMessages.DIVIDING_LINE_MESSAGE
import store.utils.message.OutputMessages.MEMBERSHIP_DISCOUNT_MESSAGE
import store.utils.message.OutputMessages.PAY_MONEY_MESSAGE
import store.utils.message.OutputMessages.PRODUCT_RECEIPT_MESSAGE
import store.utils.message.OutputMessages.PROMOTION_DISCOUNT_MESSAGE
import store.utils.message.OutputMessages.PROMOTION_RECEIPT_MESSAGE
import store.utils.message.OutputMessages.TOTAL_MONEY_MESSAGE
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
        println(PRODUCT_RECEIPT_MESSAGE)
    }

    fun printPromotionAddition(inputProductName: String) {
        println(NOW + inputProductName + PROMOTION_INTRODUCTION_INPUT_MESSAGE)
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

        println("$TOTAL_MONEY_MESSAGE${receipt.totalQuantity}\t${receipt.totalPrice.toDecimalString()}")
        println("$PROMOTION_DISCOUNT_MESSAGE${receipt.promotionDiscount.toDecimalString()}")
        println(
            "$MEMBERSHIP_DISCOUNT_MESSAGE${
                if (receipt.membershipDiscount != 0) (-receipt.membershipDiscount).toDecimalString() else ""
            }"
        )
        println("$PAY_MONEY_MESSAGE${(receipt.totalPrice - receipt.promotionDiscount - receipt.membershipDiscount).toDecimalString()}")
    }


}