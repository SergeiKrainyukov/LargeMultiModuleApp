package com.largeMultiModuleApp.core.common.result

sealed interface DomainError {
    data object Network : DomainError          // нет соединения
    data object Timeout : DomainError
    data object Unauthorized : DomainError     // 401 — нужна (повторная) аутентификация
    data class Server(val code: Int) : DomainError
    data class Unknown(val cause: Throwable?) : DomainError
}