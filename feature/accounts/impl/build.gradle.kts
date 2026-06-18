plugins {
    id("LargeMultiModuleApp.android.feature")
}
android { namespace = "com.largeMultiModuleApp.feature.accounts.impl" }
dependencies {
    implementation(project(":feature:accounts:api"))
}