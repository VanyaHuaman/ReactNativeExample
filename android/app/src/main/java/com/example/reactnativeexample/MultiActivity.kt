package com.example.reactnativeexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reactnativeexample.databinding.ActivityMultiBinding
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.soloader.SoLoader

class MultiActivity :
    AppCompatActivity(), DefaultHardwareBackBtnHandler {

    private lateinit var binding: ActivityMultiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoLoader.init(this, false)
        binding = ActivityMultiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, MultiFragment())
            .commit()
    }

    override fun invokeDefaultOnBackPressed() {
        onBackPressedDispatcher.onBackPressed()
    }
}
