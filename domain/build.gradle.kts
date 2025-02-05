plugins {
    id("java-library")
    id("kotlin-kapt")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    kapt(libs.dagger.compiler)
    implementation(project(":data"))
    implementation(libs.rxjava)
    implementation(libs.kotlinx.coroutines.core)
    // implementation(libs.kotlinx.coroutines.jdk8)
    implementation(libs.kotlinx.coroutines.rx3)
    implementation(libs.dagger)
    //implementation(libs.hilt)
    testImplementation(libs.junit)
}
