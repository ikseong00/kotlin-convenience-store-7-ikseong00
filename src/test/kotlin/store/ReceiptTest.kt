package store

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import store.model.Receipt

class ReceiptTest {
    @Test
    fun `영수증 출력 테스트`(){
        val receipt = Receipt(
            totalPrice = 10000,
            totalQuantity = 10,
            promotionDiscount = 3000,
            membershipDiscount = 1000,
            payMoney = 6000
        )

        println(receipt)
        assertEquals(
            "총구매액\t\t10\t10,000\n행사할인\t\t\t-3,000\n멤버십할인\t\t\t-1,000\n내실돈\t\t\t6,000\n",
            receipt.toString()
        )

    }
}