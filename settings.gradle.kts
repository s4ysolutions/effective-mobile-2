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

rootProject.name = "effectivem2"
include(":app")
include(":data")
include(":data-retrofit")
include(":features:messages")
include(":features:profile")
include(":features:responses")
include(":features:vacancies")
include(":shared")
include(":domain")
include(":data-room")
