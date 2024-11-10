package store.model

import store.utils.ExtensionUtil.toDecimalString

data class Stock(
    val name: String,
    val price: Int,
    var quantity: Int = 0,
    val promotion: Promotion,
    var promotionQuantity: Int = 0,
    var isPromotion: Boolean = false
) {
    fun printStock() {
        if (isPromotion) {
            println("- $name ${price.toDecimalString()}원 ${promotionQuantity}개 ${promotion.promotionName}")
        }
        if (quantity == 0) println("- $name ${price.toDecimalString()}원 재고 없음")
        else println("- $name ${price.toDecimalString()}원 ${quantity}개 ")

    }

    init {
        if (promotion != Promotion.NULL) {
            isPromotion = true
        }
    }
}