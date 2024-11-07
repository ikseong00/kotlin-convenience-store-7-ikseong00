package store.model

data class StockEntity(
    val name: String,
    val price: Int,
    val quantity: Int,
    val promotion: String
)