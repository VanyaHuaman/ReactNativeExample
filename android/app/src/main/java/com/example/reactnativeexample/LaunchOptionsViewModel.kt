package com.example.reactnativeexample

import android.os.Bundle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LaunchOptionsViewModel : ViewModel() {

    fun getLaunchOptionsForComponentString(
        componentName: String,
        baseBundle: Bundle = Bundle()
    ): Flow<Bundle> {
        val componentEnum = getReactNativeComponentEnum(componentName)
        return getLaunchOptionsForComponentEnum(componentEnum, baseBundle)
    }

    private fun getLaunchOptionsForComponentEnum(
        reactNativeComponentEnum: ReactNativeComponentEnum,
        baseBundle: Bundle
    ): Flow<Bundle> {
        return when (reactNativeComponentEnum) {
            ReactNativeComponentEnum.HELLO_COMPONENT -> {
                flow {
                    baseBundle.apply {
                        putString("fruit", "banana")
                    }
                    emit(baseBundle)
                }
            }

            ReactNativeComponentEnum.REACT_NATIVE_EXAMPLE -> {
                flow {
                    baseBundle.apply {
                        putString("cheese", "cheddar")
                    }
                    emit(baseBundle)
                }
            }

            else -> flow { emit(baseBundle) }
        }
    }

    fun getReactNativeComponentEnum(componentName: String): ReactNativeComponentEnum {
        return when (componentName.lowercase()) {
            ReactNativeComponentEnum.HELLO_COMPONENT.componentName.lowercase() -> ReactNativeComponentEnum.HELLO_COMPONENT
            ReactNativeComponentEnum.REACT_NATIVE_EXAMPLE.componentName.lowercase() -> ReactNativeComponentEnum.REACT_NATIVE_EXAMPLE
            else -> ReactNativeComponentEnum.UNKNOWN
        }
    }
}