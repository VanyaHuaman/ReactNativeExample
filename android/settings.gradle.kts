import groovy.lang.Closure

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ReactNativeExample"
include(":app")
includeBuild("../node_modules/@react-native/gradle-plugin")

apply(from = "../node_modules/@react-native-community/cli-platform-android/native_modules.gradle")
val applyNativeModules: Closure<Any> = extra.get("applyNativeModulesSettingsGradle") as Closure<Any>
applyNativeModules(settings)