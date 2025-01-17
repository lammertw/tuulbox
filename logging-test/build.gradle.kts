plugins {
    kotlin("multiplatform")
    id("kotlinx-atomicfu")
    id("org.jmailen.kotlinter")
}

kotlin {
    explicitApi()

    jvm()
    js().browser()
    macosX64()
    iosX64()
    iosArm32()
    iosArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":logging"))
            }
        }
    }
}
