package store

import camp.nextstep.edu.missionutils.Console
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import store.model.Promotion
import store.model.PurchaseProduct
import store.service.PromotionService
import store.service.StockService
import java.io.ByteArrayInputStream

class PromotionTest {

    /*
    * 프로모션 상품이 아니면 종료
    * 프로모션 재고가 부족하면 정가 결제 여부를 물어봄
    * 구매 수량이 프로모션 수량으로 나누어 떨어지면 종료
    * 프로모면 재고가 충분하면 추가 혜택 여부를 물어봄
    *
    * */
    @Test
    fun `프로모션 재고가 충분하고, 프로모션 혜택을 받을 수 있는 경우 혜택이 추가된다`() {

        val y = "Y"
        val inputStream = ByteArrayInputStream(y.toByteArray())

        System.setIn(inputStream)
        val purchaseProduct = PurchaseProduct(
            name = "콜라",
            price = 1000,
            quantity = 8,
            promotion = Promotion.CARBONIC_ACID,
            totalPrice = 8000,
            isPromotion = true,
            presentedQuantity = 0,
            discountPrice = 0
        )

        PromotionService.adaptPromotionProduct(
            purchaseProduct, stock
        )

        assertEquals(3, purchaseProduct.presentedQuantity)
//        assertEquals(9, purchaseProduct.quantity)
    }



    companion object {
        val stock = StockService.getStocks()
    }
}