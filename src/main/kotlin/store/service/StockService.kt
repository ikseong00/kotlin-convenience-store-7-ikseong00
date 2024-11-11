package store.service

import store.model.Promotion
import store.model.PurchaseProduct
import store.model.Stock
import store.utils.ExtensionUtil.toPromotion
import java.io.File

object StockService {

    private val stocks = mutableListOf<Stock>()
    private const val PRODUCTS_FILE_PATH = "src/main/resources/products.md"
    private const val NULL = "null"

    fun getStocks(): List<Stock> {
        if (stocks.isEmpty()) readProducts()
        return stocks.toList()
    }

    private fun readProducts() {
        File(PRODUCTS_FILE_PATH).useLines { lines ->
            lines.drop(1).forEach { line ->
                getProductLine(line)
            }
        }
    }

    private fun getProductLine(line: String) {
        val (name, price, quantity, promotion) = line.split(",")
        stocks.find { it.name == name }?.let {
            addQuantity(it, quantity, promotion.toPromotion())
        } ?: run {
            addStock(name, price, quantity, promotion.toPromotion())
        }
    }

    private fun addQuantity(
        stock: Stock,
        quantity: String,
        promotion: Promotion
    ) {
        if (promotion == Promotion.NULL) {
            stock.quantity += quantity.toInt()
            return
        }
        stock.promotionQuantity += quantity.toInt()
    }

    private fun addStock(name: String, price: String, quantity: String, promotion: Promotion) {
        if (promotion == Promotion.NULL) {
            addDefaultStock(name, price, quantity)
            return
        }
        addPromotionStock(name, price, quantity, promotion)
    }

    private fun addDefaultStock(name: String, price: String, quantity: String) {
        stocks.add(
            Stock(
                name, price.toInt(), quantity.toInt(), Promotion.NULL, promotionQuantity = 0
            )
        )
    }

    private fun addPromotionStock(
        name: String,
        price: String,
        quantity: String,
        promotion: Promotion
    ) {
        stocks.add(
            Stock(
                name, price.toInt(), quantity = 0, promotion, promotionQuantity = quantity.toInt()
            )
        )
    }

    fun updateStocks(purchaseProducts: List<PurchaseProduct>, stocks: List<Stock>) {
        purchaseProducts.forEach { purchaseProduct ->
            val stock = stocks.find { it.name == purchaseProduct.name }!!
            updateStock(purchaseProduct, stock)
        }
        writeStocks()
    }

    private fun updateStock(purchaseProduct: PurchaseProduct, stock: Stock) {
        when {
            !purchaseProduct.isPromotion -> stock.quantity -= purchaseProduct.quantity
            purchaseProduct.quantity > stock.promotionQuantity -> {
                stock.quantity -= purchaseProduct.quantity - stock.promotionQuantity
                stock.promotionQuantity = 0
            }

            purchaseProduct.quantity <= stock.promotionQuantity -> stock.promotionQuantity -= purchaseProduct.quantity
        }
    }

    private fun writeStocks() {
//        TODO("Not yet implemented")
    }

}