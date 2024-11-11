package store.model

import store.utils.ExtensionUtil.toDecimalString
import store.utils.ExtensionUtil.toQuantity
import store.utils.ExtensionUtil.toTextQuantity
import store.utils.message.Constants.COUNT
import store.utils.message.Constants.DASH
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
        println(
            "$DASH $name ${price.toDecimalString()}$WON " +
                    if (isPromotion) "${promotionQuantity.toQuantity()} ${promotion.promotionName}"
                    else quantity.toQuantity()
        )
    }

    fun toText(): String {
        val text = "$name,$price,${quantity.toTextQuantity()},${NULL}\n"
        if (isPromotion) {
            return "$name,$price,${promotionQuantity.toTextQuantity()},${promotion.promotionName}\n" +
                    text
        }
        return text
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