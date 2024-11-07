package store.model

data class PurchaseProduct(
    val name: String,
    var price: Int,
    val quantity: Int
) {
    init {
        price
    }

    fun printQuantityAndPrice() {
        println("$name\t\t$quantity\t${quantity * 1000}")
    }

    fun printQuantity() {
        println("$name\t\t$quantity")
    }
}
