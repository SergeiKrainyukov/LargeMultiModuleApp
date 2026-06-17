plugins {
    id("LargeMultiModuleApp.android.library")
    id("LargeMultiModuleApp.android.hilt")
}

android {
    namespace = "com.largeMultiModuleApp.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}