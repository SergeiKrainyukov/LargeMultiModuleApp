package com.largeMultiModuleApp.feature.accounts.impl

import com.largeMultiModuleApp.core.model.Account
import com.largeMultiModuleApp.core.model.Money
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

interface AccountsRepository {
    fun observeAccounts(): Flow<List<Account>>
    fun observeAccount(id: String): Flow<Account?>
}

@Singleton
class FakeAccountsRepository @Inject constructor() : AccountsRepository {
    private val accounts = listOf(
        Account("1", "Основной счёт", Money.ofMinor(123_456, "RUB")),
        Account("2", "Накопительный", Money.ofMinor(5_000_000, "RUB")),
    )
    override fun observeAccounts() = flowOf(accounts)
    override fun observeAccount(id: String) = flowOf(accounts.find { it.id == id })
}