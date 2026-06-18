package com.largeMultiModuleApp.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Брендовый шрифт. Пока — системный; когда появится лицензионный,
// замени на FontFamily(Font(R.font.brand_regular), Font(R.font.brand_medium, FontWeight.Medium), ...)
private val BankFontFamily = FontFamily.Default

// За основу берём дефолтную M3-шкалу (её метрики уже выверены) и
// прокидываем по ней только брендовый шрифт. Размеры/высоты строк не трогаем.
private val Default = Typography()

val BankTypography = Typography(
    displayLarge = Default.displayLarge.copy(fontFamily = BankFontFamily),
    displayMedium = Default.displayMedium.copy(fontFamily = BankFontFamily),
    displaySmall = Default.displaySmall.copy(fontFamily = BankFontFamily),
    headlineLarge = Default.headlineLarge.copy(fontFamily = BankFontFamily),
    headlineMedium = Default.headlineMedium.copy(fontFamily = BankFontFamily),
    headlineSmall = Default.headlineSmall.copy(fontFamily = BankFontFamily),
    titleLarge = Default.titleLarge.copy(fontFamily = BankFontFamily),
    titleMedium = Default.titleMedium.copy(fontFamily = BankFontFamily),
    titleSmall = Default.titleSmall.copy(fontFamily = BankFontFamily),
    bodyLarge = Default.bodyLarge.copy(fontFamily = BankFontFamily),
    bodyMedium = Default.bodyMedium.copy(fontFamily = BankFontFamily),
    bodySmall = Default.bodySmall.copy(fontFamily = BankFontFamily),
    labelLarge = Default.labelLarge.copy(fontFamily = BankFontFamily),
    labelMedium = Default.labelMedium.copy(fontFamily = BankFontFamily),
    labelSmall = Default.labelSmall.copy(fontFamily = BankFontFamily),
)

// --- Стили сумм ---
// Material-шкала не содержит «слота» для денег. Главное здесь — tnum:
// моноширинные (табличные) цифры, чтобы суммы выравнивались по разрядам
// и не «прыгали» в списках операций.

val AmountHero = TextStyle(
    fontFamily = BankFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 40.sp,
    lineHeight = 48.sp,
    fontFeatureSettings = "tnum",
)

val AmountRow = TextStyle(
    fontFamily = BankFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    fontFeatureSettings = "tnum",
)