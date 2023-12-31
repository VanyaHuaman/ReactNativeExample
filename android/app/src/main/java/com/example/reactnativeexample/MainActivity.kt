package com.example.reactnativeexample

import android.content.Intent
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
        binding.listNavButton.setOnClickListener {
            val listActivity = Intent(this, ListActivity::class.java)
            startActivity(listActivity)
        }
        binding.nativeWrapperNavButton.setOnClickListener {
            val nativeWrapperActivityIntent = Intent(this, NativeWrapperActivity::class.java)
            startActivity(nativeWrapperActivityIntent)
        }
        binding.multiFragNavButton.setOnClickListener {
            val multiActivity = Intent(this, MultiActivity::class.java)
            startActivity(multiActivity)
        }
    }
}
