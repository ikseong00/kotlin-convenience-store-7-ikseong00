package store.service

import store.model.PurchaseProduct
import store.model.Receipt
import store.model.UserAnswer
import store.utils.message.Constants.MAX_MEMBERSHIP_DISCOUNT
import store.utils.message.Constants.MEMBERSHIP_DISCOUNT
import store.utils.message.Constants.ZERO
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

    private fun calculateProduct(
        product: PurchaseProduct,
        receipt: Receipt,
        membership: UserAnswer
    ) {
        calculatePriceAndQuantity(product, receipt)
        if (product.isPromotion) {
            receipt.promotionDiscount += product.discountPrice
            return
        }
        setMembershipDiscount(receipt, membership, product)
    }

    private fun calculatePriceAndQuantity(product: PurchaseProduct, receipt: Receipt) {
        receipt.totalPrice += product.totalPrice
        receipt.totalQuantity += product.quantity
    }

    private fun setMembershipDiscount(
        receipt: Receipt,
        membership: UserAnswer,
        product: PurchaseProduct
    ) {
        receipt.membershipDiscount = when (membership) {
            UserAnswer.YES -> receipt.membershipDiscount + product.totalPrice
            UserAnswer.NO -> ZERO
        }
    }

    private fun calculateMembershipDiscount(receipt: Receipt, membership: UserAnswer) {
        receipt.membershipDiscount = min(
            (receipt.membershipDiscount * MEMBERSHIP_DISCOUNT).toInt(),
            MAX_MEMBERSHIP_DISCOUNT
        )
    }

    private fun calculatePayMoney(receipt: Receipt) {
        receipt.payMoney =
            receipt.totalPrice - receipt.promotionDiscount - receipt.membershipDiscount
    }
}