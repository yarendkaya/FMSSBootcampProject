pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FMSSBootcampProject"
include(":app")
include(":data")
include(":domain")
include(":presentation")
include(":presentation:sample")
include(":core")
include(":presentation:home")
include(":presentation:detail")
include(":presentation:cart")
include(":presentation:favorites")
