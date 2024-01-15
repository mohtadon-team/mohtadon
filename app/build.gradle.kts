plugins {
    id ("com.android.application")
    kotlin ("android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("androidx.navigation.safeargs.kotlin")

}


android {
    namespace = "com.example.mohtdon"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.mohtdon"
        minSdk =  24
        targetSdk = 34
        versionCode =  1
        versionName =  "1.0"

        testInstrumentationRunner =  "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField(type = "String", name = "PRAYER_TIMES_BASE_URL", value = "\"http://api.aladhan.com/\"")


        vectorDrawables {
            useSupportLibrary =  true
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
        sourceCompatibility =  JavaVersion.VERSION_1_8
        targetCompatibility =  JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    // AndroidX Core
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")


    // Project modules
    implementation(project(":domain"))
    implementation(project(":data"))

    // Play Services
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    // Compose
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.material:material:1.5.4")
    implementation (platform("androidx.compose:compose-bom:2022.10.00"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.material:material")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.activity:activity-compose")
    implementation ("androidx.compose.ui:ui-graphics")
    debugImplementation ("androidx.compose.ui:ui-tooling")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")

    // Navigation
    implementation ("androidx.navigation:navigation-compose:2.7.6")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Accompanist
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
    implementation ("com.google.accompanist:accompanist-permissions:0.32.0")

    // Coil
    implementation ("io.coil-kt:coil-compose:2.5.0")

    // CircularSeekBar
    implementation ("me.tankery.lib:circularSeekBar:1.4.2")

    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Hilt
    implementation ("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-android-compiler:2.48")
    implementation ("androidx.hilt:hilt-work:1.1.0")
    kapt ("androidx.hilt:hilt-compiler:1.1.0")

    // Work Manager
    implementation ("androidx.work:work-runtime-ktx:2.9.0")

    // Room
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    androidTestImplementation ("androidx.room:room-testing:2.6.1")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    // Gson
    implementation ("com.google.code.gson:gson:2.10.1")

    // RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    // Navigation Component
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.6")

    // Lottie
    implementation ("com.airbnb.android:lottie:6.2.0")

    // Media3
    implementation ("androidx.media3:media3-exoplayer:1.2.0")
    implementation ("androidx.media3:media3-ui:1.2.0")
    implementation ("androidx.media3:media3-common:1.2.0")
    implementation ("androidx.media3:media3-session:1.2.0")

    // Work Manager
    implementation ("androidx.work:work-runtime-ktx:2.9.0")

    // Support different screen sizes
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("com.intuit.ssp:ssp-android:1.1.0")
}

kapt {
    correctErrorTypes = true
}