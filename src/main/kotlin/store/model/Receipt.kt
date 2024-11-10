package store.model

data class Receipt(
    var totalPrice: Int = 0,
    var totalQuantity: Int = 0,
    var promotionDiscount: Int = 0,
    var membershipDiscount: Int = 0,
    var payMoney: Int = 0
)
