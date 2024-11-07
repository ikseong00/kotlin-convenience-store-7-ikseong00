package store.utils.message

object ErrorMessages {
    private const val ERROR = "[ERROR] "

    const val MONEY_INPUT_FORMAT_ERROR = ERROR + "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."
    const val PRODUCT_NOT_FOUND_ERROR = ERROR + "존재하지 않는 상품입니다. 다시 입력해 주세요."
    const val STOCK_QUANTITY_ERROR = ERROR + "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."
    const val WRONG_INPUT_ERROR = ERROR + "잘못된 입력입니다. 다시 입력해 주세요."

}