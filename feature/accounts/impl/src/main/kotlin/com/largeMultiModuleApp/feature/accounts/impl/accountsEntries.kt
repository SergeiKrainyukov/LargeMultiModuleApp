package com.largeMultiModuleApp.feature.accounts.impl

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.largeMultiModuleApp.feature.accounts.api.AccountsNavKey

fun EntryProviderScope<NavKey>.accountsEntries(
    onNavigateToDetail: (String) -> Unit,
    onBack: () -> Unit,
) {
    entry<AccountsNavKey.AccountsList> {
        AccountsListRoute(onAccountClick = onNavigateToDetail)
    }
    entry<AccountsNavKey.AccountDetail> { key ->
        // AccountDetailRoute(accountId = key.accountId, onBack = onBack)
    }
}