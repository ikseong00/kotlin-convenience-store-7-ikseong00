package store.model

data class Stock(
    val name: String,
    val price: Int,
    var quantity: Int = 0,
    val promotion: Promotion,
    var promotionQuantity: Int = 0,
    var isPromotion: Boolean = false
) {
    fun printStock() {
        if (isPromotion) {
            println("- $name ${price}원 ${promotionQuantity}개 ${promotion}")
        }
        println("- $name ${price}원 ${quantity}개 ")
    }

    init {
        if (promotion != Promotion.NULL) {
            isPromotion = true
        }
    }
}