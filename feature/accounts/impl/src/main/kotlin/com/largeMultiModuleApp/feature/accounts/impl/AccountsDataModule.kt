package com.largeMultiModuleApp.feature.accounts.impl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AccountsDataModule {
    @Binds
    abstract fun bindRepository(impl: FakeAccountsRepository): AccountsRepository
}