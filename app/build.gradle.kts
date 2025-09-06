plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // 'kotlin-parcelize'
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "ir.vy.wikipedia"
    compileSdk = 36

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "ir.vy.wikipedia"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.google.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Glide
    implementation(libs.glide)
    implementation(libs.glide.transformations)

    // convert Image to circle
    implementation(libs.circleimageview)

    // Sweet Alert Dialog
    implementation(libs.library)
    // ion alert
    implementation(libs.ionalert)

}