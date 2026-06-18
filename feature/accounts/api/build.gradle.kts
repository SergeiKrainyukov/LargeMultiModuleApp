plugins {
    id("LargeMultiModuleApp.android.library")
    alias(libs.plugins.kotlin.serialization)
}
android { namespace = "com.largeMultiModuleApp.feature.accounts.api" }
dependencies {
    api(libs.androidx.navigation3.runtime)
    implementation(libs.kotlinx.serialization.json)
}