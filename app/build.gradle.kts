plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")

}

android {
    namespace = "com.example.shoppinglistapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.shoppinglistapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Android Core Libraries
    implementation(libs.androidx.core.ktx) // Core Kotlin extensions
    implementation(libs.androidx.activity.compose) // Compose Activity integration

    // Room Database
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version") // Room runtime
    implementation("androidx.room:room-ktx:$room_version") // Room Coroutine extensions
    kapt("androidx.room:room-compiler:$room_version") // Annotation processor for Room

    // Lifecycle and ViewModel
    val lifecycle_version = "2.8.5"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version") // ViewModel with Coroutines
    implementation(libs.androidx.runtime.livedata) // LiveData
    implementation(libs.androidx.lifecycle.runtime.ktx) // Lifecycle runtime extensions

    // Jetpack Compose Libraries
    implementation(platform(libs.androidx.compose.bom)) // Compose BOM (manages Compose versions)
    implementation(libs.androidx.ui) // Compose UI toolkit
    implementation(libs.androidx.ui.graphics) // Compose graphics library
    implementation(libs.androidx.ui.tooling.preview) // Preview tools for Compose
    implementation(libs.androidx.material3) // Material Design 3 components

    // Testing Dependencies
    testImplementation(libs.junit) // JUnit for unit tests
    androidTestImplementation(libs.androidx.junit) // Android JUnit extensions
    androidTestImplementation(libs.androidx.espresso.core) // Espresso for UI testing
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Compose testing BOM
    androidTestImplementation(libs.androidx.ui.test.junit4) // Compose JUnit integration
    debugImplementation(libs.androidx.ui.tooling) // Compose tooling for debugging
    debugImplementation(libs.androidx.ui.test.manifest) // Manifest for Compose testing

}