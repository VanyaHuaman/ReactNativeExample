package com.example.reactnativeexample

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.reactnativeexample.databinding.ActivityMultiBinding
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import kotlinx.coroutines.launch

class MultiActivity :
    AppCompatActivity(), DefaultHardwareBackBtnHandler {

    private lateinit var binding: ActivityMultiBinding
    private val componentOne get() = intent?.extras?.getString(COMPONENT_ONE) ?: ""
    private val componentTwo get() = intent?.extras?.getString(COMPONENT_TWO) ?: ""
    private val source get() = intent?.extras?.getString(SOURCE) ?: ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ReactNativeApp)
            .reactNativeHost.reactInstanceManager
            .onConfigurationChanged(this, null)
        binding = ActivityMultiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launch {
            ReactNativeCoordinator.exitState.collect {
                if (it?.componentName.equals(componentOne, true) ||
                    it?.componentName.equals(componentTwo, true)
                ) {
                    finish()
                }
            }
        }

        val multiFragment = MultiFragment.newInstance(
            source = source,
            componentOne = componentOne,
            componentTwo = componentTwo,
        )

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, multiFragment)
            .commit()

        backPressHandler()
    }

    override fun invokeDefaultOnBackPressed() {
        Log.d(TAG, "invokeDefaultOnBackPressed called")
        ReactNativeCoordinator.updateExitState(componentOne)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        reactBackButtonDispatcher()
    }

    private fun backPressHandler() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                reactBackButtonDispatcher()
            }
        }
    }

    private fun reactBackButtonDispatcher() {
        (application as ReactNativeApp)
            .reactNativeHost.reactInstanceManager.onBackPressed()
    }

    companion object {
        private const val TAG = "MultiActivity"
        private const val COMPONENT_ONE = "component_one"
        private const val COMPONENT_TWO = "component_two"
        private const val SOURCE = "source"

        fun startActivity(
            context: Context,
            source: String = "",
            componentOne: String = "",
            componentTwo: String = "",
        ) {
            val intent = newIntent(
                context = context,
                source = source,
                componentOne = componentOne,
                componentTwo = componentTwo,
            )
            context.startActivity(intent)
        }

        fun newIntent(
            context: Context,
            source: String = "",
            componentOne: String = "",
            componentTwo: String = "",
        ): Intent {
            return Intent(context, MultiActivity::class.java).apply {
                putExtra(COMPONENT_ONE, componentOne)
                putExtra(COMPONENT_TWO, componentTwo)
                putExtra(SOURCE, source)
            }
        }
    }
}
