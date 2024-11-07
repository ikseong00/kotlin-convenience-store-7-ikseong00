package store.model

data class Stock(
    val name: String,
    val price: Int,
    var quantity: Int = 0,
    val promotion: Promotion,
    var promotionQuantity: Int = 0
)