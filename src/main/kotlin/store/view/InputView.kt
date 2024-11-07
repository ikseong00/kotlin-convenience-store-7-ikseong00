package store.view

import camp.nextstep.edu.missionutils.Console
import store.utils.message.InputMessages.MEMBERSHIP_DISCOUNT_INPUT_MESSAGE
import store.utils.message.InputMessages.NOW
import store.utils.message.InputMessages.PRICE_INTRODUCTION_INPUT_MESSAGE
import store.utils.message.InputMessages.PROMOTION_INTRODUCTION_INPUT_MESSAGE
import store.utils.message.InputMessages.PURCHASE_INPUT_MESSAGE

object InputView {

    fun getPurchaseInfo(): String {
        println(PURCHASE_INPUT_MESSAGE)
        return Console.readLine()
    }

    fun getPromotionAddition(inputProductName: String): String {
        println(
            NOW + inputProductName + PROMOTION_INTRODUCTION_INPUT_MESSAGE
        )
        return Console.readLine()
    }

    fun getNoneDiscountPromotion(inputProductName: String, inputProductQuantity: Int): String {
        println(
            "$NOW$inputProductName $inputProductQuantity$PRICE_INTRODUCTION_INPUT_MESSAGE"
        )
        return Console.readLine()
    }

    fun getMembership(): String {
        println(MEMBERSHIP_DISCOUNT_INPUT_MESSAGE)
        return Console.readLine()
    }

}