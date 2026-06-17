package com.largeMultiModuleApp.core.common.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val error: DomainError) : Result<Nothing>
    data object Loading : Result<Nothing>
}

/**
 * Оборачивает поток данных в Result: сначала Loading, затем Success на каждое
 * значение, а любое исключение перехватывает в Error. UI получает готовое
 * состояние и не падает от непойманного исключения в потоке.
 */
fun <T> Flow<T>.asResult(): Flow<Result<T>> =
    map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch { emit(Result.Error(DomainError.Unknown(it))) }