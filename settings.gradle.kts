pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenLocal()
    }
}

includeBuild("convention-plugins")
include(
    ":compose-app",
    ":navigation-toggle-icon"
)

rootProject.name = "navigation-toggle-icon-root"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")