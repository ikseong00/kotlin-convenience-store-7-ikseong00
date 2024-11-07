package store

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import store.utils.Validator

class ValidatorTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[콜라-3],[사이다-2],[사이다-2]",
            "[콜라-3],[사이다-2],[사이다-2],[사이다-2]",
            "[콜라-3]"
        ]
    )
    fun `구매 품목과 구매 수량 입력 자체의 유효성을 검증한다`(text: String) {
        assertDoesNotThrow {
            Validator.validatePurchaseInfo(text)
        }
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[콜[라-3]",
            "[콜]라-3]",
            "[콜-라-3]",
            "[콜,라-3]",
            "[콜라-3],[사이다--2]",
            "[콜라-3],",
            "[콜라-3],[사이다-2],[사]",
            "[콜라-3],[사이다-2],[-2]",
            "[콜라-3],[사이다-2],[환타-]",
        ])
    fun `구매 품목과 구매 수량 입력 자체의 오류를 검증한다`(text: String) {
        assertThrows<IllegalArgumentException> {
            Validator.validatePurchaseInfo(text)
        }
    }

    companion object {


    }

}