package com.example.reactnativeexample

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.reactnativeexample.databinding.ActivityNativeWrapperBinding
import com.facebook.react.ReactFragment
import com.facebook.react.ReactInstanceManager
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.soloader.SoLoader

class NativeWrapperActivity :
    AppCompatActivity(), DefaultHardwareBackBtnHandler {

    private lateinit var binding: ActivityNativeWrapperBinding
    private lateinit var reactInstanceManager: ReactInstanceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoLoader.init(this, false)
        binding = ActivityNativeWrapperBinding.inflate(layoutInflater)
        binding.toolbarBackButton.setOnClickListener {
            invokeDefaultOnBackPressed()
        }
        val view = binding.root
        setContentView(view)

        val reactNativeFragment = ReactFragment.Builder()
            .setComponentName("HelloComponent")
            .setLaunchOptions(getLaunchOptions("test message"))
            .setFabricEnabled(false)
            .build()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.reactNativeFragment, reactNativeFragment)
            .commit()
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }

    private fun getLaunchOptions(message: String) = Bundle().apply {
        putString("message", message)
    }

    override fun onBackPressed() {
        reactInstanceManager.onBackPressed()
        super.onBackPressed()
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_MENU && reactInstanceManager != null) {
            reactInstanceManager.showDevOptionsDialog()
            return true
        }
        return super.onKeyUp(keyCode, event)
    }
}
