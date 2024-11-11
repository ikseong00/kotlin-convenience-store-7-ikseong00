package store.model

import store.utils.message.Constants
import store.utils.message.Constants.CARBONIC_ACID_PROMOTION
import store.utils.message.Constants.EMPTY
import store.utils.message.Constants.FLASH_SALE_PROMOTION
import store.utils.message.Constants.MD_RECOMMEND_PROMOTION
import java.time.LocalDateTime

enum class Promotion(
    val promotionName: String,
    private val buy: Int,
    val get: Int,
    var promotionCount: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime
) {

    // TODO : 파일에서 읽어오는 기능을 추가해야 함
    CARBONIC_ACID(
        CARBONIC_ACID_PROMOTION, 2, 1, 3,
        LocalDateTime.of(2024, 1, 1, 0, 0),
        LocalDateTime.of(2024, 12, 31, 0, 0)
    ),
    MD_RECOMMEND(
        MD_RECOMMEND_PROMOTION, 1, 1, 2,
        LocalDateTime.of(2024, 1, 1, 0, 0),
        LocalDateTime.of(2024, 12, 31, 0, 0)
    ),
    FLASH_SALE(
        FLASH_SALE_PROMOTION, 1, 1, 2,
        LocalDateTime.of(2024, 11, 1, 0, 0),
        LocalDateTime.of(2024, 11, 30, 0, 0)
    ),
    NULL(
        EMPTY, 0, 0, 0, LocalDateTime.MIN, LocalDateTime.MIN
    );

    init {
        promotionCount = buy + get
    }

    fun isAddable(quantity: Int): Boolean = quantity % promotionCount == buy
    fun getPresentedQuantity(quantity: Int): Int = quantity / promotionCount

}