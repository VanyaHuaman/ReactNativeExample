package com.example.reactnativeexample

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class ExitModule(reactApplicationContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactApplicationContext) {
    override fun getName(): String = EXIT_MODULE

    @ReactMethod
    fun exitComponent(componentName: String?) {
        ReactNativeCoordinator.updateExitState(componentName)
    }

    companion object {
        private const val EXIT_MODULE = "ExitModule"
    }
}