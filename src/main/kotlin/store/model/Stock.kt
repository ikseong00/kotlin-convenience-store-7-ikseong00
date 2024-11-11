package store.model

import store.utils.ExtensionUtil.toDecimalString
import store.utils.ExtensionUtil.toQuantity
import store.utils.ExtensionUtil.toTextQuantity
import store.utils.message.Constants.COUNT
import store.utils.message.Constants.DASH
import store.utils.message.Constants.EMPTY
import store.utils.message.Constants.NULL
import store.utils.message.Constants.WON

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
            println(stockInfo(promotionQuantity, promotion))
        }
        println(stockInfo(quantity, promotion))

    }

    private fun stockInfo(quantity: Int, promotion: Promotion): String {
        return "$DASH $name ${price.toDecimalString()}$WON ${quantity.toQuantity()}" +
                if (promotion != Promotion.NULL) " ${promotion.promotionName}" else EMPTY
    }

    fun toPromotionText(): String {
        return "$name,$price,${promotionQuantity.toTextQuantity()},${promotion.promotionName}\n"
    }

    fun toText(): String {
        return "$name,$price,${quantity.toTextQuantity()},${NULL}\n"
    }

    fun toProduct(purchaseQuantity: Int) =
        PurchaseProduct(
            name = name,
            price = price,
            quantity = purchaseQuantity,
            promotion = promotion,
            isPromotion = isPromotion
        )

    init {
        if (promotion != Promotion.NULL) {
            isPromotion = true
        }
    }
}