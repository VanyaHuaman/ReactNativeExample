package com.example.reactnativeexample

import android.app.Application
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.defaults.DefaultReactNativeHost

class ReactNativeApp : Application(), ReactApplication {

    private val reactNativeHost =
        object : DefaultReactNativeHost(this) {
            override fun getUseDeveloperSupport() = BuildConfig.DEBUG
            override fun getPackages(): List<ReactPackage> {
                val packages = PackageList(this).packages.toMutableList()
                // Packages that cannot be autolinked yet can be added manually here
                packages.add(NativeAppPackage())
                return packages
            }

            override fun getJSMainModuleName() = "index"
            override val isNewArchEnabled: Boolean = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
            override val isHermesEnabled: Boolean = BuildConfig.IS_HERMES_ENABLED
            override fun getBundleAssetName(): String = "index.android.bundle"
        }

    override fun getReactNativeHost(): ReactNativeHost = reactNativeHost
}
