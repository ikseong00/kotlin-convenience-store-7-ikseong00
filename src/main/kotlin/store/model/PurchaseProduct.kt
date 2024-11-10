package store.model

data class PurchaseProduct(
    val name: String,
    var price: Int,
    var quantity: Int,
    val promotion: Promotion,
    var totalPrice: Int = 0,
    var isPromotion: Boolean = false,
    var presentedQuantity: Int = 0,
    var discountPrice: Int = 0
) {
    init {
        totalPrice = price * quantity
    }

    fun printQuantityAndPrice() {
        println("$name\t\t$quantity\t${quantity * 1000}")
    }

    fun printPresentedQuantity() {
        println("$name\t\t$presentedQuantity")
    }
}
