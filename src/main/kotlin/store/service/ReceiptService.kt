package store.service

import store.model.PurchaseProduct
import store.model.Receipt
import store.model.UserAnswer
import kotlin.math.max
import kotlin.math.min

object ReceiptService {

    fun createReceipt(purchaseProducts: List<PurchaseProduct>, membership: UserAnswer): Receipt {
        return writeReceipt(purchaseProducts, membership)
    }

    private fun writeReceipt(
        purchaseProducts: List<PurchaseProduct>,
        membership: UserAnswer
    ): Receipt {
        val receipt = Receipt()
        purchaseProducts.forEach { product ->
            calculateProduct(product, receipt, membership)
        }
        calculateMembershipDiscount(receipt, membership)
        calculatePayMoney(receipt)
        return receipt
    }

    private fun calculateMembershipDiscount(receipt: Receipt, membership: UserAnswer) {
        receipt.membershipDiscount = min(
            (receipt.membershipDiscount * 0.3).toInt(),
            8000
        )
    }

    private fun calculateProduct(
        product: PurchaseProduct,
        receipt: Receipt,
        membership: UserAnswer
    ) {
        receipt.totalPrice += product.totalPrice
        receipt.totalQuantity += product.quantity
        if (product.isPromotion) {
            receipt.promotionDiscount += product.discountPrice
            return
        }
        receipt.membershipDiscount = when (membership) {
            UserAnswer.YES -> receipt.membershipDiscount + product.totalPrice
            UserAnswer.NO -> 0
        }
    }

    private fun calculatePayMoney(receipt: Receipt) {
        receipt.payMoney =
            receipt.totalPrice - receipt.promotionDiscount - receipt.membershipDiscount
    }
}