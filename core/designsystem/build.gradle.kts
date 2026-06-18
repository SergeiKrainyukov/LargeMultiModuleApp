plugins {
    id("LargeMultiModuleApp.android.library.compose")
}

android {
    namespace = "com.myapp.core.designsystem"
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
}