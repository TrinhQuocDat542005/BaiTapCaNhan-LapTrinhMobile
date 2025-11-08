plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // ✅ Đảm bảo dòng này đã có ở trên cùng
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.uthsmarttasks"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.uthsmarttasks"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        // ✅ Sửa lại version cho đúng
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {

    // --- Jetpack Compose ---
    // Khai báo BoM (Bill of Materials) - nó sẽ tự quản lý phiên bản
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))

    // Các thư viện Compose (KHÔNG cần ghi phiên bản, BoM sẽ tự lo)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // --- Compose Navigation & Activity (Cần phiên bản cụ thể) ---
    implementation("androidx.navigation:navigation-compose:2.7.7") // 👈 ✅ SỬA LỖI "thiếu nav"
    implementation("androidx.activity:activity-compose:1.9.0")

    // --- AndroidX core ---
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")

    // --- Firebase (dùng BoM để đồng bộ version) ---
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:21.1.1")

    // --- Retrofit & Networking ---
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0") // 👈 ✅ SỬA LỖI libs.logging

    // --- Coil (nếu dùng để load ảnh) ---
    implementation("io.coil-kt:coil-compose:2.7.0")

    // --- Testing ---
    testImplementation("junit:junit:4.13.2") // 👈 ✅ SỬA LỖI libs.junit
    androidTestImplementation("androidx.test.ext:junit:1.2.1") // 👈 ✅ SỬA LỖI libs.androidx.junit
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1") // 👈 ✅ SỬA LỖI libs.androidx.espresso.core
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.05.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4") // 👈 ✅ SỬA LỖI libs.androidx.compose.ui.test.junit4
}