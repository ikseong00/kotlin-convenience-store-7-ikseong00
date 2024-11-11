package store.model

import store.utils.ExtensionUtil.toDecimalString
import store.utils.message.OutputMessages.CHANGE_LINE
import store.utils.message.OutputMessages.MEMBERSHIP_DISCOUNT_MESSAGE
import store.utils.message.OutputMessages.PAY_MONEY_MESSAGE
import store.utils.message.OutputMessages.PROMOTION_DISCOUNT_MESSAGE
import store.utils.message.OutputMessages.TOTAL_MONEY_MESSAGE

data class Receipt(
    var totalPrice: Int = 0,
    var totalQuantity: Int = 0,
    var promotionDiscount: Int = 0,
    var membershipDiscount: Int = 0,
    var payMoney: Int = 0
) {
    override fun toString(): String {
        var receiptInfo = ""

        receiptInfo += "$TOTAL_MONEY_MESSAGE${totalQuantity}\t${totalPrice.toDecimalString()}$CHANGE_LINE"
        receiptInfo += "$PROMOTION_DISCOUNT_MESSAGE${promotionDiscount.toDecimalString()}$CHANGE_LINE"
        receiptInfo +=
                "$MEMBERSHIP_DISCOUNT_MESSAGE${
                    if (membershipDiscount != 0) (-membershipDiscount).toDecimalString() else ""
                }$CHANGE_LINE"

        receiptInfo += "$PAY_MONEY_MESSAGE${(totalPrice - promotionDiscount - membershipDiscount).toDecimalString()}$CHANGE_LINE"

        return receiptInfo
    }
}
