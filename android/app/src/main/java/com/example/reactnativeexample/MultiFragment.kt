package com.example.reactnativeexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reactnativeexample.databinding.FragmentMultiBinding
import com.facebook.react.ReactFragment

class MultiFragment : Fragment() {
    private lateinit var binding: FragmentMultiBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMultiBinding.inflate(layoutInflater, container, false)
        binding.toolbarBackButton.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reactComponentOne = ReactFragment.Builder()
            .setComponentName("HelloComponent")
            .setLaunchOptions(getLaunchOptions("test message"))
            .setFabricEnabled(false)
            .build()
        val reactComponentTwo = ReactFragment.Builder()
            .setComponentName("ReactNativeExample")
            .setLaunchOptions(getLaunchOptions("test message"))
            .setFabricEnabled(false)
            .build()

        childFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_one, reactComponentOne)
            .commit()

        childFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_two, reactComponentTwo)
            .commit()
    }

    private fun getLaunchOptions(message: String) = Bundle().apply {
        putString("message", message)
    }
}
