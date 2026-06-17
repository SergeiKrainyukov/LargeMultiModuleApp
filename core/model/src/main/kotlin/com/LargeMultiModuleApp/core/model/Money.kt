package com.largeMultiModuleApp.core.model

import java.math.BigDecimal
import java.util.Currency

/**
 * Денежная сумма, хранимая в минорных единицах (копейки/центы) как Long.
 * Создаётся только через фабрики — конструктор internal, чтобы исключить
 * случайное создание с неверным масштабом.
 */
data class Money private constructor(
    val minorUnits: Long,
    val currency: Currency,
) : Comparable<Money> {

    companion object {
        /** Из минорных единиц: ofMinor(1099, USD) == $10.99 */
        fun ofMinor(minorUnits: Long, currency: Currency): Money =
            Money(minorUnits, currency)

        fun ofMinor(minorUnits: Long, currencyCode: String): Money =
            Money(minorUnits, Currency.getInstance(currencyCode))

        /** Из мажорных единиц с проверкой точности: ofMajor("10.50", USD). */
        fun ofMajor(amount: BigDecimal, currency: Currency): Money {
            val scaled = amount.movePointRight(currency.defaultFractionDigits)
            require(scaled.stripTrailingZeros().scale() <= 0) {
                "Сумма $amount точнее, чем допускает ${currency.currencyCode}"
            }
            return Money(scaled.longValueExact(), currency)
        }

        fun zero(currency: Currency): Money = Money(0L, currency)
    }

    operator fun plus(other: Money): Money {
        requireSameCurrency(other)
        return Money(Math.addExact(minorUnits, other.minorUnits), currency)
    }

    operator fun minus(other: Money): Money {
        requireSameCurrency(other)
        return Money(Math.subtractExact(minorUnits, other.minorUnits), currency)
    }

    operator fun times(factor: Int): Money =
        Money(Math.multiplyExact(minorUnits, factor.toLong()), currency)

    override fun compareTo(other: Money): Int {
        requireSameCurrency(other)
        return minorUnits.compareTo(other.minorUnits)
    }

    val isPositive: Boolean get() = minorUnits > 0
    val isNegative: Boolean get() = minorUnits < 0
    val isZero: Boolean get() = minorUnits == 0L

    /** Перевод в мажорные единицы для форматирования: 1099 -> 10.99 */
    fun toBigDecimal(): BigDecimal =
        BigDecimal.valueOf(minorUnits, currency.defaultFractionDigits)

    private fun requireSameCurrency(other: Money) {
        require(currency == other.currency) {
            "Операция над разными валютами: $currency и ${other.currency}"
        }
    }
}