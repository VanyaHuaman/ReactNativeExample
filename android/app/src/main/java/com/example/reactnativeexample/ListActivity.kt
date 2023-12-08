package com.example.reactnativeexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reactnativeexample.databinding.ActivityListBinding
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.soloader.SoLoader

class ListActivity :
    AppCompatActivity(), DefaultHardwareBackBtnHandler {

    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoLoader.init(this, false)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, ListFragment())
            .commit()
    }

    override fun invokeDefaultOnBackPressed() {
        onBackPressedDispatcher.onBackPressed()
    }
}
