package store.utils

import camp.nextstep.edu.missionutils.DateTimes
import store.model.Promotion
import java.time.LocalDateTime

object DateTimeUtil {

    private fun isWithinRange(
        targetDate: LocalDateTime,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Boolean {
        return (targetDate.isEqual(startDate) || targetDate.isAfter(startDate)) &&
                (targetDate.isEqual(endDate) || targetDate.isBefore(endDate))
    }

    fun promotionFinished(promotion: Promotion): Boolean {
        val nowDate = DateTimes.now()
        return !isWithinRange(nowDate, promotion.startDate, promotion.endDate)

    }
}
