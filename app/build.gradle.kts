plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("LargeMultiModuleApp.android.hilt")        // тянет ksp + hilt-android (com.android.base уже есть)
}

android {
    namespace = "com.skrainyukov.largemultimoduleapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.largeMultiModuleApp"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures { compose = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    // твои buildTypes/signing как обычно
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    implementation(project(":feature:accounts:api"))
    implementation(project(":feature:accounts:impl"))   // фича (api подтянется транзитивно)
    implementation(project(":core:designsystem"))       // BankTheme

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    testImplementation(libs.junit)
}