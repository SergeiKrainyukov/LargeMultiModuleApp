@file:Suppress("NonAsciiCharacters")

package com.largeMultiModuleApp.core.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.util.Currency

class MoneyTest {

    private val usd = Currency.getInstance("USD")
    private val eur = Currency.getInstance("EUR")
    private val jpy = Currency.getInstance("JPY")

    @Test
    fun `складывает суммы одной валюты точно`() {
        // 10.99 + 20.01 = 31.00, без артефактов плавающей точки
        val result = Money.ofMinor(1099, usd) + Money.ofMinor(2001, usd)
        assertEquals(Money.ofMinor(3100, usd), result)
    }

    @Test
    fun `запрещает смешивать валюты`() {
        assertThrows<IllegalArgumentException> {
            Money.ofMinor(100, usd) + Money.ofMinor(100, eur)
        }
    }

    @Test
    fun `парсит мажорные единицы с учётом масштаба валюты`() {
        assertEquals(Money.ofMinor(1050, usd), Money.ofMajor(BigDecimal("10.50"), usd))
    }

    @Test
    fun `JPY не имеет дробной части`() {
        // у иены 0 знаков: 500 минорных == 500 мажорных
        assertEquals(Money.ofMinor(500, jpy), Money.ofMajor(BigDecimal("500"), jpy))
    }

    @Test
    fun `отвергает избыточную точность`() {
        assertThrows<IllegalArgumentException> {
            Money.ofMajor(BigDecimal("10.555"), usd) // у USD только 2 знака
        }
    }

    @Test
    fun `переполнение при сложении бросает исключение`() {
        val max = Money.ofMinor(Long.MAX_VALUE, usd)
        assertThrows<ArithmeticException> { max + Money.ofMinor(1, usd) }
    }
}