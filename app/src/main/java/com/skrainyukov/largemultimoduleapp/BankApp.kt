package com.skrainyukov.largemultimoduleapp

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.largeMultiModuleApp.feature.accounts.api.AccountsNavKey
import com.largeMultiModuleApp.feature.accounts.impl.accountsEntries

@Composable
fun BankApp() {
    val backStack = rememberNavBackStack(AccountsNavKey.AccountsList)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            accountsEntries(
                onNavigateToDetail = { id -> backStack.add(AccountsNavKey.AccountDetail(id)) },
                onBack = { backStack.removeLastOrNull() },
            )
        },
    )
}