package store.service

import store.model.Promotion
import store.model.PurchaseProduct
import store.model.Stock
import store.model.UserAnswer

object PromotionService {

    fun adaptPromotionProduct(product: PurchaseProduct, stocks: List<Stock>) {
        if (product.promotion == Promotion.NULL) return
        initPresentedQuantity(product, stocks)
        when (checkPromotionStock(product, stocks)) {
            true -> setPresentedQuantity(product)
            false -> {}
        }
    }

    private fun initPresentedQuantity(product: PurchaseProduct, stocks: List<Stock>) {
        product.presentedQuantity = 3 - product.quantity % 3
    }

    private fun checkPromotionStock(product: PurchaseProduct, stocks: List<Stock>): Boolean {
        val promotionStock = stocks.find { it.name == product.name }!!
        val promotionCount = promotionStock.promotion.promotionCount
        val availablePromotionQuantity =
            if (product.quantity % promotionCount == 0) 0 else promotionCount - product.quantity % promotionCount

        return product.quantity + availablePromotionQuantity <= promotionStock.promotionQuantity
    }

    private fun setPresentedQuantity(product: PurchaseProduct) {
        if (product.promotion.isFitPromotionQuantity(product.quantity)) return
        when (InputService.getPromotionQuantityAddition(product.name)) {
            UserAnswer.YES -> addPresentedQuantity(product)
            UserAnswer.NO -> return
        }
    }

    private fun addPresentedQuantity(product: PurchaseProduct) {
        val newPromotionQuantity = product.promotion.getPromotionQuantity(product.quantity)
        product.quantity += newPromotionQuantity
        product.presentedQuantity += newPromotionQuantity
    }
}