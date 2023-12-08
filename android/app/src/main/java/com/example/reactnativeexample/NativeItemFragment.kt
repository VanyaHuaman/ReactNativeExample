package com.example.reactnativeexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reactnativeexample.databinding.FragmentNativeItemBinding

class NativeItemFragment : Fragment() {

    private lateinit var binding: FragmentNativeItemBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNativeItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
