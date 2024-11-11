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
        if (isPromotion) {
            println("$DASH $name ${price.toDecimalString()}$WON ${promotionQuantity.toQuantity()} ${promotion.promotionName}")
        }
        println("$DASH $name ${price.toDecimalString()}$WON ${quantity.toQuantity()}")
    }

    fun toText(): String {
        if (isPromotion) {
            return "$name,$price,${promotionQuantity.toTextQuantity()},${promotion.promotionName}\n" +
                    "$name,$price,${quantity.toTextQuantity()},${Promotion.NULL}\n"
        }
        return "$name,$price,${quantity.toTextQuantity()},${NULL}\n"
    }

    init {
        if (promotion != Promotion.NULL) {
            isPromotion = true
        }
    }
}