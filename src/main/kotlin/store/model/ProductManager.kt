package store.model

import java.io.File

class ProductManager() {

    private val products = mutableListOf<Product>()

    init {
        readProducts()
    }

    private fun readProducts() {
        val filepath = "products.md"
        File(filepath).forEachLine {
            val split = it.split(",")
            products.add(
                Product(
                    split[0],
                    split[1].toInt(),
                    split[2].toInt(),
                    split[3]
                )
            )
        }
    }

}