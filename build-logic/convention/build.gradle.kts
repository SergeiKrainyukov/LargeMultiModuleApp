import org.gradle.kotlin.dsl.dependencies

plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.hilt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "LargeMultiModuleApp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("jvmLibrary") {
            id = "LargeMultiModuleApp.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "LargeMultiModuleApp.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "LargeMultiModuleApp.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "LargeMultiModuleApp.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
    }
}