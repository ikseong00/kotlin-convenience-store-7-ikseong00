package store.view

import store.model.PurchaseProduct
import store.model.Stock

object OutputView {
    fun printWelcomeMessage() {
        println("안녕하세요. W편의점입니다.")
        println("현재 보유하고 있는 상품입니다.")
    }

    fun printStocks(stocks: List<Stock>) {
        stocks.forEach {
            it.printStock()
        }
    }

    fun printProductReceipt(purchaseProducts: List<PurchaseProduct>) {
        println("==============W 편의점================")
        println("상품명\t\t수량\t금액")
        purchaseProducts.forEach {
            it.printQuantityAndPrice()
        }
    }

    fun printPromotionReceipt(purchaseProducts: List<PurchaseProduct>) {
        println("=============증\t정===============")
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
        println("====================================")
        println("총구매액\t\t$totalQuantity\t${totalPrice}")
        println("행사할인\t\t\t-${promotionDiscount}")
        println("멤버십할인\t\t\t-${membershipDiscount}")
        println("내실돈\t\t\t${totalPrice - promotionDiscount - membershipDiscount}")
    }


}