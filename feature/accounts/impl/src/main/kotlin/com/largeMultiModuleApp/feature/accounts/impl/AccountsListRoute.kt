package com.largeMultiModuleApp.feature.accounts.impl

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.largeMultiModuleApp.core.designsystem.theme.BankTheme
import com.largeMultiModuleApp.core.model.Account
import com.largeMultiModuleApp.core.model.Money

@Composable
fun AccountsListRoute(
    onAccountClick: (String) -> Unit,
    viewModel: AccountsListViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    AccountsListScreen(state = state, onAccountClick = onAccountClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AccountsListScreen(
    state: AccountsUiState,
    onAccountClick: (String) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Счета") },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        when {
            state.isLoading -> Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }

            state.error != null -> Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center,
            ) {
                Text("Не удалось загрузить счета")
            }

            else -> LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = innerPadding,
            ) {
                items(
                    state.accounts,
                    key = { it.id }) { account ->
                    AccountRow(
                        account = account,
                        onClick = { onAccountClick(account.id) },
                    )
                }
            }
        }
    }
}

@Composable
private fun AccountRow(
    account: Account,
    onClick: () -> Unit,
) {
    ListItem(
        headlineContent = { Text(account.name) },
        trailingContent = {
            // временно — переедет в AmountText (:core:ui), там же красим по знаку
            Text("${account.balance.toBigDecimal()} ${account.balance.currency.currencyCode}")
        },
        modifier = Modifier.clickable(onClick = onClick),
    )
}

@Preview
@Composable
private fun AccountsListPreview() {
    BankTheme {
        AccountsListScreen(
            state = AccountsUiState(
                isLoading = false,
                accounts = listOf(
                    Account("1", "Основной счёт", Money.ofMinor(123_456, "RUB")),
                    Account("2", "Накопительный", Money.ofMinor(5_000_000, "RUB")),
                ),
            ),
            onAccountClick = {},
        )
    }
}