plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.binance"
    compileSdk = 36 // Anh có thể hạ xuống 34 nếu 36 bị lỗi

    defaultConfig {
        applicationId = "com.example.binance"
        minSdk = 24
        targetSdk = 36 // Anh có thể hạ xuống 34 nếu 36 bị lỗi
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
    // Thêm dòng này để Compose dùng được file build
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Phiên bản cho Compose (quan trọng)
    }
}

// ⚠️ ANH XÓA HẾT DEPENDENCIES CŨ VÀ DÙNG CÁI NÀY NHÉ!
dependencies {
    implementation(libs.moshi.kotlin)
    implementation(libs.material3)
    implementation(libs.androidx.activity.compose)
    // ----- CORE VÀ ACTIVITY -----
    implementation(libs.androidx.core.ktx.v1131)
    implementation(libs.androidx.activity.compose.v190)

    // ----- COMPOSE (BOM - Bill of Materials) -----
    // Dùng 1 cái BOM sẽ tự quản lý phiên bản cho các thư viện Compose khác
    implementation(platform(libs.androidx.compose.bom.v20251100))
    implementation(libs.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // ----- LIFECYCLE, VIEWMODEL & STATE (Để sửa lỗi đỏ) -----
    implementation(libs.androidx.lifecycle.runtime.ktx.v281)
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v281)
    implementation(libs.androidx.lifecycle.viewmodel.compose) // 🚀 SỬA LỖI viewModel()
    implementation(libs.androidx.compose.runtime) // 🚀 SỬA LỖI collectAsState()

    // ----- RETROFIT (Gọi API) -----
    implementation(libs.retrofit)
    implementation(libs.converter.moshi) // Dùng Moshi
    implementation(libs.logging.interceptor.v4120) // (Để xem log)

    // ----- MOSHI (Phân tích JSON) -----
    implementation(libs.moshi.kotlin.v1151)

    // ----- COROUTINES (Chạy nền) -----
    implementation(libs.kotlinx.coroutines.core.v180)
    implementation(libs.kotlinx.coroutines.android.v180)

    // ----- COIL (Tải ảnh) -----
    implementation(libs.coil.compose.v260)

    // ----- TEST (Giữ nguyên) -----
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    androidTestImplementation(platform(libs.androidx.compose.bom.v20251100))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}