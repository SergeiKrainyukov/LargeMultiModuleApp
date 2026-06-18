package com.largeMultiModuleApp.feature.accounts.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AccountsNavKey : NavKey {
    @Serializable
    data object AccountsList : AccountsNavKey

    @Serializable
    data class AccountDetail(val accountId: String) : AccountsNavKey
}