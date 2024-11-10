package store.model

data class PurchaseProduct(
    val name: String,
    var price: Int,
    var quantity: Int,
    val promotion: Promotion,
    var totalPrice: Int = 0,
    var isPromotion: Boolean = false,
    val presentedQuantity: Int = 0,
    val discountPrice: Int = 0
) {

    fun printQuantityAndPrice() {
        println("$name\t\t$quantity\t${quantity * 1000}")
    }

    fun printQuantity() {
        println("$name\t\t$quantity")
    }
}
