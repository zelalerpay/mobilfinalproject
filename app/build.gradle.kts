
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "msku.ceng.madlab.uniguide"
    compileSdk = 35

    defaultConfig {
        applicationId = "msku.ceng.madlab.uniguide"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.common)
    implementation(libs.room.runtime)
    implementation(libs.core.testing)
    implementation(libs.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    annotationProcessor(libs.room.compiler)
    implementation (libs.appcompat.v161)
    implementation (libs.material.v180)
    testImplementation(libs.core.testing)
    implementation(libs.room.runtime.v250)
    annotationProcessor(libs.room.compiler.v250)
    testImplementation(libs.room.testing)
    testImplementation (libs.core)
    testImplementation (libs.junit)








}