package store.view

import store.model.Stock

object OutputView {
    fun printWelcomeMessage() {
        println("안녕하세요. W편의점입니다.")
        println("현재 보유하고 있는 상품입니다.")
    }

    fun printStocks(stocks: List<Stock>) {
        stocks.forEach {
            it.printStock()
        }
    }

    private fun printStock(stock: Stock) {
//        - 콜라 1,000원 10개 탄산2+1
        if (stock.isPromotion) {
            println("- ${stock.name} ${stock.price}원 ${stock.promotionQuantity}개 ${stock.promotion}")
        }
        println("- ${stock.name} ${stock.price}원 ${stock.quantity}개 ")
    }
}