package store.utils

import store.model.PurchaseProduct
import store.model.Promotion
import store.model.Stock

object ExtensionUtil {

    private const val NULL = "null"
    private const val EMPTY = ""

    fun String.toPromotion() =
        when (this) {
            Promotion.CARBONIC_ACID.promotionName -> Promotion.CARBONIC_ACID
            Promotion.MD_RECOMMEND.promotionName -> Promotion.MD_RECOMMEND
            Promotion.FLASH_SALE.promotionName -> Promotion.FLASH_SALE
            else -> Promotion.NULL
        }

    fun Stock.toProduct(): PurchaseProduct =
        PurchaseProduct(name, quantity)

}