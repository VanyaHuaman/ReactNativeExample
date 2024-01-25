package com.example.reactnativeexample

import kotlinx.coroutines.flow.MutableStateFlow

object ReactNativeCoordinator {
    val exitState: MutableStateFlow<ReactNativeExitState?> = MutableStateFlow(null)

    fun updateExitState(componentName: String?) {
        exitState.tryEmit(ReactNativeExitState(componentName))
        exitState.tryEmit(null)
    }
}