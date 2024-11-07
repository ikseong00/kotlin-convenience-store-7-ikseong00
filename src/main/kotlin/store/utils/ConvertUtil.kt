package store.utils

import store.model.Product
import store.model.Promotion
import store.model.StockEntity

object ConvertUtil {

    fun String.toPromotion() =
        when (this) {
            Promotion.CARBONIC_ACID.promotionName -> Promotion.CARBONIC_ACID
            Promotion.MD_RECOMMEND.promotionName -> Promotion.MD_RECOMMEND
            Promotion.FLASH_SALE.promotionName -> Promotion.FLASH_SALE
            else -> Promotion.NULL
        }

    fun StockEntity.toProduct(): Product =
        Product(name, price, quantity, promotion.toPromotion())

}