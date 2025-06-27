plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "org.alex"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":library"))

    implementation(libs.kotlinx.coroutines)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}