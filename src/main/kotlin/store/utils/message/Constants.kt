package store.utils.message

object Constants {
    const val CARBONIC_ACID_PROMOTION = "탄산2+1"
    const val MD_RECOMMEND_PROMOTION = "MD추천상품"
    const val FLASH_SALE_PROMOTION = "반짝할인"

    const val NULL = "null"
    const val PRODUCTS_FILE_PATH = "src/main/resources/products.md"
    const val CHANGE_LINE = "\n"
    const val DECIMAL_FORMAT = "%,d"
    const val NO_STOCK = "재고 없음"
    const val COUNT = "개"
    const val EMPTY = ""
    const val DASH = "-"
    const val WON = "원"
    const val DEFAULT_STOCKS = "name,price,quantity,promotion\n" +
            "콜라,1000,10,탄산2+1\n" +
            "콜라,1000,10,null\n" +
            "사이다,1000,8,탄산2+1\n" +
            "사이다,1000,7,null\n" +
            "오렌지주스,1800,9,MD추천상품\n" +
            "탄산수,1200,5,탄산2+1\n" +
            "물,500,10,null\n" +
            "비타민워터,1500,6,null\n" +
            "감자칩,1500,5,반짝할인\n" +
            "감자칩,1500,5,null\n" +
            "초코바,1200,5,MD추천상품\n" +
            "초코바,1200,5,null\n" +
            "에너지바,2000,5,null\n" +
            "정식도시락,6400,8,null\n" +
            "컵라면,1700,1,MD추천상품\n" +
            "컵라면,1700,10,null\n"
}