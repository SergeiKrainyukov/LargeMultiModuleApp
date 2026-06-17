package com.largeMultiModuleApp.core.common.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: LargeMultiModuleAppDispatchers)

enum class LargeMultiModuleAppDispatchers {
    Default,
    IO,
}