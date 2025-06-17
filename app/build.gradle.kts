plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.ipekkochisarli.forinvest_crypto"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ipekkochisarli.forinvest_crypto"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL_COINS",
                "\"https://api.coingecko.com/\""
            )
            buildConfigField(
                "String",
                "BASE_URL_NEWS",
                "\"https://newsapi.org/\""
            )
            buildConfigField(
                "Boolean",
                "DEBUG_MODE",
                "true"
            )
            buildConfigField(
                "String",
                "NEWS_API_KEY",
                "\"${properties["NEWS_API_KEY"]}\""
            )
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "BASE_URL_COINS",
                "\"https://api.coingecko.com/\""
            )
            buildConfigField(
                "String",
                "BASE_URL_NEWS",
                "\"https://newsapi.org/\""
            )
            buildConfigField(
                "Boolean",
                "DEBUG_MODE",
                "false"
            )
            buildConfigField(
                "String",
                "NEWS_API_KEY",
                "\"${properties["NEWS_API_KEY"]}\""
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

    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //coroutines
    implementation(libs.kotlinx.coroutines.android)

    //hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.paging.runtime.ktx)

    implementation(libs.androidx.navigation.fragment)
    ksp(libs.hilt.android.compiler)

    //roomdb
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)

    //splash
    implementation(libs.androidx.core.splashscreen)
    //glide
    implementation(libs.glide)

    //navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //viewpager2
    implementation(libs.androidx.viewpager2)
    //dots indicator
    implementation(libs.dotsindicator)

    //okhttp
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // chart
    implementation (libs.mpandroidchart)

    implementation(libs.material.v160)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}