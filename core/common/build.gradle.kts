plugins {
    id("LargeMultiModuleApp.android.library")
    id("LargeMultiModuleApp.android.hilt")
}

android {
    namespace = "com.largeMultiModuleApp.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}