package com.largeMultiModuleApp.feature.accounts.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.largeMultiModuleApp.core.common.result.DomainError
import com.largeMultiModuleApp.core.common.result.asResult
import com.largeMultiModuleApp.core.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class AccountsUiState(
    val isLoading: Boolean = true,
    val accounts: List<Account> = emptyList(),
    val error: DomainError? = null,
)

@HiltViewModel
class AccountsListViewModel @Inject constructor(
    repository: AccountsRepository,
) : ViewModel() {

    val uiState: StateFlow<AccountsUiState> =
        repository.observeAccounts()
            .asResult()
            .map { result ->
                when (result) {
                    com.largeMultiModuleApp.core.common.result.Result.Loading -> AccountsUiState(isLoading = true)
                    is com.largeMultiModuleApp.core.common.result.Result.Success -> AccountsUiState(isLoading = false, accounts = result.data)
                    is com.largeMultiModuleApp.core.common.result.Result.Error -> AccountsUiState(isLoading = false, error = result.error)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = AccountsUiState(isLoading = true),
            )
}