package store.model

import store.utils.ConvertUtil.toPromotion
import java.io.File

class ProductManager() {

    private val stocks = mutableListOf<StockEntity>()

    init {
        readStocks()
    }

    private fun readStocks() {
        val filepath = "products.md"
        File(filepath).forEachLine {
            val split = it.split(",")
            stocks.add(
                StockEntity(
                    split[0],
                    split[1].toInt(),
                    split[2].toInt(),
                    split[3].toPromotion()
                )
            )
        }
    }



}