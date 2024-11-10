package store.service

import camp.nextstep.edu.missionutils.DateTimes
import store.model.Promotion
import store.model.PurchaseProduct
import store.model.Stock
import store.model.UserAnswer
import store.utils.DateTimeUtil.promotionFinished

object PromotionService {

    fun adaptPromotionProduct(product: PurchaseProduct, stocks: List<Stock>) {
        if (product.promotion == Promotion.NULL) return
        if (promotionFinished(product.promotion)) return
        initPresentedAndDiscount(product, stocks)
        val promotionStock = stocks.find { it.name == product.name }!!
        when (checkPromotionStock(product, promotionStock)) {
            true -> setPresentedQuantity(product)
            false -> askDefaultPrice(product, promotionStock)
        }
    }


    private fun initPresentedAndDiscount(product: PurchaseProduct, stocks: List<Stock>) {
        product.presentedQuantity = product.promotion.getPresentedQuantity(product.quantity)
        product.discountPrice = product.presentedQuantity * product.price
    }

    private fun checkPromotionStock(product: PurchaseProduct, promotionStock: Stock): Boolean {
        val promotionCount = promotionStock.promotion.promotionCount
        val availablePromotionQuantity =
            if (product.quantity % promotionCount == 0) 0 else promotionCount - product.quantity % promotionCount

        return product.quantity + availablePromotionQuantity <= promotionStock.promotionQuantity
    }

    private fun setPresentedQuantity(product: PurchaseProduct) {
        if (product.promotion.isAddable(product.quantity)) {
            when (InputService.getPromotionQuantityAddition(product.name)) {
                UserAnswer.YES -> addPresentedQuantity(product)
                UserAnswer.NO -> return
            }
        }
    }

    private fun addPresentedQuantity(product: PurchaseProduct) {
        product.quantity += 1
        product.presentedQuantity += 1
    }

    private fun askDefaultPrice(product: PurchaseProduct, promotionStock: Stock) {
        val defaultPriceQuantity = product.quantity - promotionStock.promotionQuantity

        when (InputService.getDefaultPricePurchase(product.name, defaultPriceQuantity)) {
            UserAnswer.YES -> return
            UserAnswer.NO -> {
                product.quantity -= defaultPriceQuantity
            }
        }
    }
}
