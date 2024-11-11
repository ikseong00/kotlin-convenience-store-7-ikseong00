package store

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import store.model.Promotion
import store.model.PurchaseProduct
import store.service.PromotionService
import store.service.StockService
import java.io.ByteArrayInputStream

class PromotionTest : NsTest() {

    /*
    * 프로모션 상품이 아니면 종료
    * 프로모션 재고가 부족하면 정가 결제 여부를 물어봄
    * 구매 수량이 프로모션 수량으로 나누어 떨어지면 종료
    * 프로모면 재고가 충분하면 추가 혜택 여부를 물어봄
    *
    * */
    @Test
    fun `프로모션 재고가 충분하고, 프로모션 혜택을 받을 수 있는 경우 혜택이 추가된다`() {
        assertSimpleTest {
            run("[콜라-8]", "Y", "N", "N")
            assertThat(output().replace("\\s".toRegex(), "")).contains("콜라3")
        }
    }

    @Test
    fun `프로모션 재고가 충분하고, 프로모션 혜택을 거절하면 그대로 계산된다`() {
        assertSimpleTest {
            run("[콜라-8]", "N", "N", "N")
            assertThat(output().replace("\\s".toRegex(), "")).contains("콜라2")
        }
    }

    @Test
    fun `프로모션 재고가 부족하고, 정가 결제를 거부할 때 구매 수량이 조정된다`() {
        assertSimpleTest {
            run("[콜라-11]", "N", "N", "N")
            assertThat(output().replace("\\s".toRegex(), "")).contains("콜라9")
        }
    }

    @Test
    fun `프로모션 재고가 부족하고, 정가 결제를 승인할 때 그대로 계산된다`() {
        assertSimpleTest {
            run("[콜라-11]", "N", "N", "N")
            assertThat(output().replace("\\s".toRegex(), "")).contains("콜라9")
        }
    }

    @Test
    fun `프로모션 재고를 초과한 구매를 했을 때, 프로모션 개수가 제대로 정해지는 지 확인한다`() {
        assertSimpleTest {
            run("[콜라-12]", "Y", "N", "N")
            assertThat(output().replace("\\s".toRegex(), "")).contains("콜라3")
        }
    }

    @Test
    fun `프로모션 재고를 초과한 구매를 했을 때, 재고가 잘 처리 되는지 확인한다`() {
        assertSimpleTest {
            run("[콜라-12]", "Y", "N", "Y", "[물-1]", "N", "N")
            assertThat(output().replace("\\s".toRegex(), "")).contains(
                "-콜라1,000원재고없음탄산2+1"
            )
        }
    }


    override fun runMain() {
        main()
    }
}