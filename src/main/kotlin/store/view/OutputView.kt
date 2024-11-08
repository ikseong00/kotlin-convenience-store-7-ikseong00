package store.view

import store.model.PurchaseProduct
import store.model.Stock
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
            it.printQuantity()
        }
    }

    fun printTotalMoneyReceipt(
        totalPrice: Int,
        totalQuantity: Int,
        promotionDiscount: Int,
        membershipDiscount: Int
    ) {
        println(DIVIDING_LINE_MESSAGE)

        println("$TOTAL_MONEY_MESSAGE$totalQuantity\t${totalPrice}")
        println("$PROMOTION_DISCOUNT_MESSAGE${promotionDiscount}")
        println("$MEMBERSHIP_DISCOUNT_MESSAGE${membershipDiscount}")
        println("$PAY_MONEY_MESSAGE${totalPrice - promotionDiscount - membershipDiscount}")
    }


}