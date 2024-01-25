package com.example.reactnativeexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reactnativeexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.nativeWrapperNavButton.setOnClickListener {
            NativeWrapperActivity
                .startActivity(
                    this,
                    ReactNativeComponentEnum.HELLO_COMPONENT.componentName,
                    "MainActivity"
                )
        }
        binding.multiFragNavButton.setOnClickListener {
            MultiActivity
                .startActivity(
                    context = this,
                    source = "MainActivity",
                    componentOne = ReactNativeComponentEnum.REACT_NATIVE_EXAMPLE.componentName,
                    componentTwo = ReactNativeComponentEnum.HELLO_COMPONENT.componentName,
                )
        }
    }
}
