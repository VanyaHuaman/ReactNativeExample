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
import com.example.reactnativeexample.databinding.ActivityNativeWrapperBinding
import com.facebook.react.ReactFragment
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import kotlinx.coroutines.launch

class NativeWrapperActivity :
    AppCompatActivity(), DefaultHardwareBackBtnHandler {

    private val componentName get() = intent.extras?.getString(COMPONENT) ?: ""
    private lateinit var binding: ActivityNativeWrapperBinding
    private val launchOptionsViewModel = LaunchOptionsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ReactNativeApp)
            .reactNativeHost.reactInstanceManager
            .onConfigurationChanged(this, null)
        binding = ActivityNativeWrapperBinding.inflate(layoutInflater)
        binding.toolbarBackButton.setOnClickListener {
            reactBackButtonDispatcher()
        }
        val view = binding.root
        setContentView(view)
        lifecycleScope.launch {
            ReactNativeCoordinator.exitState.collect {
                if (it?.componentName.equals(componentName, true)) {
                    finish()
                }
            }
        }
        lifecycleScope.launch {
            launchOptionsViewModel.getLaunchOptionsForComponentString(componentName, getBaseLaunchOptions()).collect {
                val reactNativeFragment = ReactFragment.Builder()
                    .setComponentName(componentName)
                    .setLaunchOptions(it)
                    .setFabricEnabled(false)
                    .build()
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.reactNativeFragment, reactNativeFragment)
                    .commit()
            }
        }
        backPressHandler()
    }

    override fun invokeDefaultOnBackPressed() {
        Log.d(TAG, "invokeDefaultOnBackPressed called")
        // React native code could use the EXITMODULE instead
        ReactNativeCoordinator.updateExitState(componentName)
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

    private fun getBaseLaunchOptions(): Bundle {
        val baseOptions = Bundle()
        val extras = intent.extras
        extras?.getString(SOURCE)?.let {
            baseOptions.putString(SOURCE, it)
        }
        return baseOptions
    }

    companion object {
        private const val TAG = "NativeWrapperActivity"
        const val COMPONENT = "component"
        const val SOURCE = "source"

        fun startActivity(
            context: Context,
            component: String,
            source: String = "",
        ) {
            val intent = newIntent(
                context = context,
                component = component,
                source = source,
            )
            context.startActivity(intent)
        }

        fun newIntent(
            context: Context,
            component: String,
            source: String = "",
        ): Intent {
            return Intent(context, NativeWrapperActivity::class.java).apply {
                putExtra(COMPONENT, component)
                putExtra(SOURCE, source)
            }
        }
    }
}
