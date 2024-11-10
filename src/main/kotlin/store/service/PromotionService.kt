package store.service

import camp.nextstep.edu.missionutils.DateTimes
import store.model.Promotion
import store.model.PurchaseProduct
import store.model.Stock
import store.model.UserAnswer
import store.utils.DateTimeUtil.promotionFinished
import kotlin.math.min

object PromotionService {

    fun adaptPromotionProduct(product: PurchaseProduct, stocks: List<Stock>) {
        if (product.promotion == Promotion.NULL) return
        if (promotionFinished(product.promotion)) return
        val promotionStock = stocks.find { it.name == product.name }!!
        initPresentedAndDiscount(product, promotionStock)
        when (checkPromotionStock(product, promotionStock)) {
            true -> setPresentedQuantity(product)
            false -> askDefaultPrice(product, promotionStock)
        }
    }


    private fun initPresentedAndDiscount(product: PurchaseProduct, promotionStock: Stock) {

        product.presentedQuantity = min(
            product.promotion.getPresentedQuantity(product.quantity),
            promotionStock.promotionQuantity / promotionStock.promotion.promotionCount
        )
        product.discountPrice = product.presentedQuantity * product.price
    }

    private fun checkPromotionStock(product: PurchaseProduct, promotionStock: Stock): Boolean {
        val promotionCount = promotionStock.promotion.promotionCount
        val availablePromotionQuantity =
            if (product.quantity % promotionCount == 0) 0 else promotionCount - product.quantity % promotionCount

        return product.quantity + availablePromotionQuantity < promotionStock.promotionQuantity
    }

    private fun setPresentedQuantity(product: PurchaseProduct) {
        if (product.promotion.isAddable(product.quantity)) {
            when (InputService.getPromotionQuantityAddition(product.name)) {
                UserAnswer.YES -> setProduct(1, product)
                UserAnswer.NO -> return
            }
        }
    }

    private fun setProduct(quantity: Int, product: PurchaseProduct) {
        product.quantity += quantity
        product.presentedQuantity += quantity
        product.totalPrice += product.price
        product.discountPrice += product.price
    }

    private fun askDefaultPrice(product: PurchaseProduct, promotionStock: Stock) {
        val defaultPriceQuantity = product.quantity - promotionStock.promotionQuantity

        when (InputService.getDefaultPricePurchase(product.name, defaultPriceQuantity)) {
            UserAnswer.YES -> return
            UserAnswer.NO -> {
                setProduct(defaultPriceQuantity, product)
            }
        }
    }
}
