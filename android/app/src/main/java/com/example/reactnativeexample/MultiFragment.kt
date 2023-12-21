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

    private val crNumber = "11503550945"

    val reactComponentOne = ReactFragment.Builder()
        .setComponentName("HelloComponent")
        .setFabricEnabled(false)
        .build()
    val reactComponentTwo = ReactFragment.Builder()
        .setComponentName("MessageComponent")
        .setLaunchOptions(getLaunchOptions())
        .setFabricEnabled(false)
        .build()

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

        childFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_one, reactComponentOne)
            .commit()

        childFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_two, reactComponentTwo)
            .commit()
    }

    fun getLaunchOptions(): Bundle = Bundle().apply {
        putString(CR_NUMBER_KEY, crNumber)
    }

    companion object {
        private const val CR_NUMBER_KEY = "cr_number"
    }
}
