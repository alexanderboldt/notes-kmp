plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "org.alex"
version = "1.0.0"

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.okhttp)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.multiplatform.settings)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.coroutines.test)
            }
        }
    }
}
