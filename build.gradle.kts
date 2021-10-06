import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.ANDROID_GRADLE}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_GRADLE_PLUGIN}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT_AGP}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("com.diffplug.gradle.spotless") version "3.27.1"
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}


subprojects {
    apply(plugin = "com.diffplug.gradle.spotless")
    val ktlintVer = "0.40.0"
    spotless {
        kotlin {
            target("**/*.kt")
            ktlint(ktlintVer)
        }
        kotlinGradle {
            // same as kotlin, but for .gradle.kts files (defaults to '*.gradle.kts')
            target("**/*.gradle.kts")
            ktlint(ktlintVer)
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs +=
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }
}
