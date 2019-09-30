import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetPreset

plugins {
    kotlin("multiplatform") version "1.3.41"
}

repositories {
    mavenCentral()
}

kotlin {
    presets.withType<KotlinNativeTargetPreset>().filter { it.name == "macosX64" || it.name == "linuxX64" }.forEach {
        targetFromPreset(it) {
            compilations.getByName("main") {
                val interop by cinterops.creating {
                    defFile(project.file("src/nativeInterop/cinterop/libsdl.def"))
                }

                binaries {
                    executable() {
                        entryPoint = "io.mattmoore.gameengine.main"
                        baseName = "engine"
                    }
                }
            }
        }
    }

    sourceSets {
        val macosX64Main by getting {
            kotlin.srcDir("src/nativeMain/kotlin")
        }
        val linuxX64Main by getting {
            kotlin.srcDir("src/nativeMain/kotlin")
        }
    }
}

// kotlin {
//     val hostOs = System.getProperty("os.name")
// 
//     val hostTarget = when {
//         hostOs == "Mac OS X" -> macosX64("libsdl")
//         hostOs == "Linux" -> linuxX64("libsdl")
//         else -> throw GradleException("Host OS '$hostOs' is not supported in Kotlin/Native $project.")
//     }
// 
//     hostTarget.apply {
//         compilations["main"].cinterops {
//             val sdl by creating {
//                 when(preset) {
//                     presets["macosX64"] -> includeDirs.headerFilterOnly("/opt/local/include", "/usr/local/include")
//                     presets["linuxX64"] -> includeDirs.headerFilterOnly("/usr/include", "/usr/include/x86_64-linux-gnu")
//                     else -> throw GradleException("Host OS '$hostOs' is not supported in Kotlin/Native $project.")
//                 }
//             }
//         }
//     }
// }
