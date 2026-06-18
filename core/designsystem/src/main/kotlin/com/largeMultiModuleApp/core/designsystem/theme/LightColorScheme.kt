package com.largeMultiModuleApp.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(primary = BankBlue)
private val DarkColorScheme = darkColorScheme(primary = BankBlueDark)

private val LightSemantic = BankSemanticColors(MoneyPositiveLight, MoneyNegativeLight)
private val DarkSemantic = BankSemanticColors(MoneyPositiveDark, MoneyNegativeDark)

@Composable
fun BankTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,   // для банка выключено: бренд > персонализации
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val ctx = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val semantic = if (darkTheme) DarkSemantic else LightSemantic

    CompositionLocalProvider(LocalBankSemanticColors provides semantic) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = BankTypography,
            content = content,
        )
    }
}