package store.controller

import store.service.StockService
import store.model.PurchaseProduct
import store.service.InputService
import store.service.PromotionService

// 0. 파일로부터 정보를 읽어옴
// 1. 구매 수량과 품목을 입력받음
// 2. 프로모션 상품인지 확인
// 2.1. 프로모션 상품이고, 프로모션 재고가 충분한 지 확인
// 2.1.1. 프로모션 재고가 충분하면, 추가 혜택 여부를 물어봄
// 2.1.2. 프로모션 재고가 부족하면, 정가 결제 여부를 물어봄
// 2.2 일반 상품이면, 그냥 계산함

// 3. 멤버십 여부를 물어봄
// 4. 총 가격과 수량을 계산
// 5. 영수증 출력
// 6. 재고 상황 업데이트
// 7. 추가 구매 여부를 물어봄.
// 7.1. 추가 구매 시 1번부터 다시 시작
// 7.2. 아니면 프로그램 종료
class StoreController {

    private val stocks = StockService.getStocks()
    private var purchaseProducts: List<PurchaseProduct> =
        InputService.getPurchaseInfoToProducts(stocks)

    fun run() {
        purchaseProducts.forEach {
            PromotionService.adaptPromotionProduct(it, stocks)
        }

    }

}
