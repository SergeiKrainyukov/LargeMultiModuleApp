import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("LargeMultiModuleApp.android.library.compose")
        pluginManager.apply("LargeMultiModuleApp.android.hilt")

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        dependencies {
            "implementation"(project(":core:designsystem"))
            "implementation"(project(":core:common"))
            "implementation"(project(":core:model"))
            "implementation"(libs.findLibrary("androidx-hilt-navigation-compose").get())
            "implementation"(libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
            "implementation"(libs.findLibrary("androidx-lifecycle-runtime-compose").get())
            "implementation"(libs.findLibrary("androidx-navigation3-runtime").get())
            "implementation"(libs.findLibrary("androidx-navigation3-ui").get())
        }
    }
}