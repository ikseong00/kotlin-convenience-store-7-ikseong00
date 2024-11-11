package store.utils.message

object OutputMessages {
    // TODO: 출력 메시지 상수화
    const val WELCOME_MESSAGE = "안녕하세요. W편의점입니다.\n" +
            "현재 보유하고 있는 상품입니다.\n"

    const val PURCHASE_INPUT_MESSAGE = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"
    const val NOW = "현재 "
    const val PROMOTION_INTRODUCTION_INPUT_MESSAGE = "은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)"
    const val PRICE_INTRODUCTION_INPUT_MESSAGE = "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)"
    const val MEMBERSHIP_DISCOUNT_INPUT_MESSAGE = "멤버십 할인을 받으시겠습니까? (Y/N)"

    const val W_CONVENIENCE_STORE = "==============W 편의점================"
    const val PRODUCT_RECEIPT_MESSAGE = "상품명\t\t수량\t금액"
    const val PROMOTION_RECEIPT_MESSAGE = "=============증정==============="
    const val DIVIDING_LINE_MESSAGE = "===================================="

    const val TOTAL_MONEY_MESSAGE = "총구매액\t\t"
    const val PROMOTION_DISCOUNT_MESSAGE = "행사할인\t\t\t-"
    const val MEMBERSHIP_DISCOUNT_MESSAGE = "멤버십할인\t\t\t"
    const val PAY_MONEY_MESSAGE = "내실돈\t\t\t"

    const val REPURCHASE_INPUT_MESSAGE = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"

}