// Trong tệp settings.gradle.kts

pluginManagement {
    repositories {
        // Đảm bảo có các dòng này
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Đảm bảo có các dòng này
        google()
        mavenCentral()
        // Nếu bạn dùng JitPack, thêm dòng này
        // maven("https://jitpack.io") 
    }
}

rootProject.name = "UTH-SmartTasks" // (Tên dự án của bạn)
include(":app")