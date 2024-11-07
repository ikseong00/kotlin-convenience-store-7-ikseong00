package store.utils

import store.model.Promotion

object ConvertUtil {

    fun String.toPromotion() =
        when (this) {
            Promotion.CARBONIC_ACID.promotionName -> Promotion.CARBONIC_ACID
            Promotion.MD_RECOMMEND.promotionName -> Promotion.MD_RECOMMEND
            Promotion.FLASH_SALE.promotionName -> Promotion.FLASH_SALE
            else -> Promotion.NULL
        }

}