package com.example.reactnativeexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reactnativeexample.databinding.FragmentListBinding
import com.facebook.react.ReactFragment

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var listAdapter: ListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        binding.toolbarBackButton.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentList = mutableListOf<Fragment>()
        val listFragment = ReactFragment.Builder()
            .setComponentName("HelloComponent")
            .setLaunchOptions(getLaunchOptions("test message"))
            .setFabricEnabled(false)
            .build()
        val reactNativeFragment = ReactFragment.Builder()
            .setComponentName("ReactNativeExample")
            .setLaunchOptions(getLaunchOptions("test message"))
            .setFabricEnabled(false)
            .build()

        fragmentList.add(listFragment)
        fragmentList.add(reactNativeFragment)
        fragmentList.add(NativeItemFragment())
        listAdapter = ListAdapter(fragmentList, childFragmentManager)

        binding.recyclerView.adapter = listAdapter
    }

    private fun getLaunchOptions(message: String) = Bundle().apply {
        putString("message", message)
    }
}
