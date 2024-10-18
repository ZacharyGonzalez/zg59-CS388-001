import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.joker"
    compileSdk = 34

    // Enable BuildConfig fields
    buildFeatures {
        buildConfig = true
    }

    val localPropertiesFile = file("C:\\NJIT\\zg59-CS388-001\\Extra Credit\\app\\local.properties")
    val localProperties = Properties()
    localProperties.load(FileInputStream(localPropertiesFile))

    defaultConfig {
        applicationId = "com.example.joker"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Load API key from apikey.properties
        buildConfigField("String", "API_KEY", localProperties["apiKey"].toString())

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
        debug {
            // Optionally, you can also add the API key to the debug build type
            buildConfigField("String", "JOKE_API_KEY", "\"${properties["JOKE_API_KEY"]}\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.loopj.android:android-async-http:1.4.9")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.foundation.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
