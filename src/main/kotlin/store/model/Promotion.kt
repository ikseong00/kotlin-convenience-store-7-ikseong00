package store.model

import java.time.LocalDateTime

enum class Promotion(
    val promotionName: String?,
    val buy: Int?,
    val get: Int?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    var promotionCount: Int? = 0
) {

    // TODO : 파일에서 읽어오는 기능을 추가해야 함
    CARBONIC_ACID(
        "탄산2+1", 2, 1,
        LocalDateTime.of(2024, 1, 1, 0, 0),
        LocalDateTime.of(2024, 12, 31, 0, 0)
    ),
    MD_RECOMMEND(
        "MD추천상품", 1, 1,
        LocalDateTime.of(2024, 1, 1, 0, 0),
        LocalDateTime.of(2024, 12, 31, 0, 0)
    ),
    FLASH_SALE(
        "반짝할인", 1, 1,
        LocalDateTime.of(2024, 11, 1, 0, 0),
        LocalDateTime.of(2024, 11, 30, 0, 0)
    ),
    NULL(
        null, null, null, null, null
    );

    init {
        promotionCount = buy?.plus(get!!)
    }
    fun isAddable(quantity: Int): Boolean = quantity % promotionCount!! == buy

    fun getPresentedQuantity(quantity: Int): Int = quantity / promotionCount!!

}