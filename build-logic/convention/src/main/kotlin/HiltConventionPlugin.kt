import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        // KSP нужен в любом случае — это процессор аннотаций Hilt/Dagger
        pluginManager.apply("com.google.devtools.ksp")
        dependencies {
            "ksp"(libs.findLibrary("hilt.compiler").get())
        }

        // Hilt Android-рантайм и его Gradle-плагин — только для Android-модулей.
        // com.android.base применяется и library-, и application-плагином,
        // поэтому withPlugin срабатывает на обоих.
        pluginManager.withPlugin("com.android.base") {
            pluginManager.apply("com.google.dagger.hilt.android")
            dependencies {
                "implementation"(libs.findLibrary("hilt.android").get())
            }
        }
    }
}