package com.example.reactnativeexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(
    private val dataSet: MutableList<Fragment>,
    private val fragmentManager: FragmentManager,
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_element, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        fragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, dataSet[position])
            .commit()
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val fragmentContainerView: FragmentContainerView

        init {
            fragmentContainerView = view.findViewById(R.id.fragment_container)
            textView = view.findViewById(R.id.text_label)
        }
    }
}
