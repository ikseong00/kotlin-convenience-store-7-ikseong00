package store

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import store.model.Promotion
import store.model.Stock
import store.service.StockService

class StockTest {
    @Test
    fun `재고 정보를 읽어오는지 확인한다`() {

        val stocks = StockService.getStocks()



        assertEquals(stocks, fileStocks)
    }

    companion object {
        val fileStocks = listOf(
            Stock(
                name = "콜라",
                price = 1000,
                quantity = 10,
                promotion = Promotion.CARBONIC_ACID,
                promotionQuantity = 10
            ),
            Stock(
                name = "사이다",
                price = 1000,
                quantity = 7,
                promotion = Promotion.CARBONIC_ACID,
                promotionQuantity = 8
            ),
            Stock(
                name = "오렌지주스",
                price = 1800,
                quantity = 0,
                promotion = Promotion.MD_RECOMMEND,
                promotionQuantity = 9
            ),
            Stock(
                name = "탄산수",
                price = 1200,
                quantity = 0,
                promotion = Promotion.CARBONIC_ACID,
                promotionQuantity = 5
            ),
            Stock(
                name = "물",
                price = 500,
                quantity = 10,
                promotion = Promotion.NULL,
                promotionQuantity = 0
            ),
            Stock(
                name = "비타민워터",
                price = 1500,
                quantity = 6,
                promotion = Promotion.NULL,
                promotionQuantity = 0
            ),
            Stock(
                name = "감자칩",
                price = 1500,
                quantity = 5,
                promotion = Promotion.FLASH_SALE,
                promotionQuantity = 5
            ),
            Stock(
                name = "초코바",
                price = 1200,
                quantity = 5,
                promotion = Promotion.MD_RECOMMEND,
                promotionQuantity = 5
            ),
            Stock(
                name = "에너지바",
                price = 2000,
                quantity = 5,
                promotion = Promotion.NULL,
                promotionQuantity = 0
            ),
            Stock(
                name = "정식도시락",
                price = 6400,
                quantity = 8,
                promotion = Promotion.NULL,
                promotionQuantity = 0
            ),
            Stock(
                name = "컵라면",
                price = 1700,
                quantity = 10,
                promotion = Promotion.MD_RECOMMEND,
                promotionQuantity = 1
            )
        )
    }
}