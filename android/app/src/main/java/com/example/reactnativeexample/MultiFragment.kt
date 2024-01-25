package com.example.reactnativeexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.reactnativeexample.databinding.FragmentMultiBinding
import com.facebook.react.ReactFragment
import kotlinx.coroutines.launch

class MultiFragment : Fragment() {
    private lateinit var binding: FragmentMultiBinding
    private val launchOptionsViewModel = LaunchOptionsViewModel()
    private val componentOne get() = arguments?.getString(COMPONENT_ONE) ?: ""
    private val componentTwo get() = arguments?.getString(COMPONENT_TWO) ?: ""

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

        lifecycleScope.launch {
            launchOptionsViewModel.getLaunchOptionsForComponentString(
                componentOne,
                getBaseLaunchOptions()
            ).collect {
                val reactComponentOne = ReactFragment.Builder()
                    .setComponentName(componentOne)
                    .setLaunchOptions(it)
                    .setFabricEnabled(false)
                    .build()

                childFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_one, reactComponentOne)
                    .commit()
            }
        }

        lifecycleScope.launch {
            launchOptionsViewModel.getLaunchOptionsForComponentString(
                componentTwo,
                getBaseLaunchOptions()
            ).collect {
                val reactComponentTwo = ReactFragment.Builder()
                    .setComponentName(componentTwo)
                    .setLaunchOptions(it)
                    .setFabricEnabled(false)
                    .build()

                childFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_two, reactComponentTwo)
                    .commit()
            }
        }
    }

    private fun getBaseLaunchOptions(): Bundle {
        val baseOptions = Bundle()
        val extras = arguments
        extras?.getString(NativeWrapperActivity.SOURCE)?.let {
            baseOptions.putString(NativeWrapperActivity.SOURCE, it)
        }
        return baseOptions
    }


    companion object {
        private const val TAG = "MultiFragment"
        private const val COMPONENT_ONE = "component_one"
        private const val COMPONENT_TWO = "component_two"
        private const val SOURCE = "source"

        fun newInstance(
            source: String = "",
            componentOne: String = "",
            componentTwo: String = "",
        ): MultiFragment {
            val fragment = MultiFragment()
            val args = Bundle().apply {
                putString(SOURCE, source)
                putString(COMPONENT_ONE, componentOne)
                putString(COMPONENT_TWO, componentTwo)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
