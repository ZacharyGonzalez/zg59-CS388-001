package com.example.doyouloveme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailsFragment : Fragment() {

    private lateinit var selectedItem: BitFitItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedItem = it.getParcelable(ARG_ITEM)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        val itemNameTextView: TextView = view.findViewById(R.id.ItemNameTextView)
        val caloriesTextView: TextView = view.findViewById(R.id.CaloriesTextView)

        itemNameTextView.text = selectedItem.itemName
        caloriesTextView.text = selectedItem.calories

        return view
    }

    companion object {
        private const val ARG_ITEM = "item"

        fun newInstance(item: BitFitItem): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putParcelable(ARG_ITEM, item)
            fragment.arguments = args
            return fragment
        }
    }
}


