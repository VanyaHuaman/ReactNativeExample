import com.facebook.react.utils.KotlinStdlibCompatUtils.toBooleanStrictOrNullCompat
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.facebook.react")
}

apply(from = "../reactConfig.gradle")

val jscFlavor = "org.webkit:android-jsc-intl:+"

android {
    namespace = "com.example.reactnativeexample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.reactnativeexample"
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
                "proguard-rules.pro",
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("com.facebook.react:react-android")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val hermesEnabled: String by rootProject
    if (hermesEnabled.toBooleanStrictOrNullCompat() == true) {
        implementation("com.facebook.react:hermes-android")
    } else {
        implementation(jscFlavor)
    }
}

apply(from = "../../node_modules/@react-native-community/cli-platform-android/native_modules.gradle")
val applyNativeModulesAppBuildGradle: groovy.lang.Closure<Any> by extra
applyNativeModulesAppBuildGradle(project, null)
