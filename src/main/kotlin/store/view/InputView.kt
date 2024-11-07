package store.view

import camp.nextstep.edu.missionutils.Console
import store.utils.Validator
import store.utils.message.InputMessages.PURCHASE_INPUT_MESSAGE

object InputView {
    fun getPurchaseInfo(): String {
        println(PURCHASE_INPUT_MESSAGE)
        return Console.readLine()
    }



}