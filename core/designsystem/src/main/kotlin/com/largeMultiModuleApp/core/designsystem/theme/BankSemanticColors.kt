package com.largeMultiModuleApp.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class BankSemanticColors(
    val positive: Color,
    val negative: Color,
)

val LocalBankSemanticColors = staticCompositionLocalOf {
    BankSemanticColors(positive = Color.Unspecified, negative = Color.Unspecified)
}

// удобный доступ: bankSemanticColors.positive внутри @Composable
val bankSemanticColors: BankSemanticColors
    @Composable @ReadOnlyComposable
    get() = LocalBankSemanticColors.current