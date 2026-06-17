package com.largeMultiModuleApp.core.common.result

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ResultTest {

    @Test
    @DisplayName("эмитит Loading, затем Success для каждого значения")
    fun emitsLoadingThenSuccess() = runTest {
        val results = flowOf(42).asResult().toList()

        assertEquals(2, results.size)
        assertEquals(Result.Loading, results[0])
        assertEquals(Result.Success(42), results[1])
    }

    @Test
    @DisplayName("любое исключение в потоке перехватывается в Error")
    fun mapsExceptionToError() = runTest {
        val results = flow<Int> { throw RuntimeException("boom") }.asResult().toList()

        assertEquals(Result.Loading, results[0])
        val error = assertInstanceOf(Result.Error::class.java, results[1])
        assertTrue(error.error is DomainError.Unknown)
    }
}