package store.service

import store.model.Promotion
import store.model.PurchaseProduct
import store.model.Stock

object PromotionService {


    fun adaptPromotionProduct(product: PurchaseProduct, stocks: List<Stock>) {
        if (product.promotion == Promotion.NULL) return
        if (checkPromotionStock(product, stocks)) {
            addPresentedQuantity(product)
        }
    }

    private fun addPresentedQuantity(product: PurchaseProduct) {

    }

    private fun checkPromotionStock(product: PurchaseProduct, stocks: List<Stock>): Boolean {
        val promotionStock = stocks.find { it.name == product.name }!!

        return product.quantity <= promotionStock.promotionQuantity
    }
}