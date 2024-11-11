package store.utils

import store.model.PurchaseProduct
import store.model.Promotion
import store.model.Stock
import store.utils.message.Constants.COUNT
import store.utils.message.Constants.DECIMAL_FORMAT
import store.utils.message.Constants.NO_STOCK

object ExtensionUtil {

    fun String.toPromotion() =
        when (this) {
            Promotion.CARBONIC_ACID.promotionName -> Promotion.CARBONIC_ACID
            Promotion.MD_RECOMMEND.promotionName -> Promotion.MD_RECOMMEND
            Promotion.FLASH_SALE.promotionName -> Promotion.FLASH_SALE
            else -> Promotion.NULL
        }

    fun Stock.toProduct(purchaseQuantity: Int) =
        PurchaseProduct(
            name = name,
            price = price,
            quantity = purchaseQuantity,
            promotion = promotion,
            isPromotion = isPromotion
        )

    fun Int.toDecimalString() = String.format(DECIMAL_FORMAT, this)

    fun Int.toQuantity() = if (this <= 0) NO_STOCK else (this.toString() + COUNT)

}