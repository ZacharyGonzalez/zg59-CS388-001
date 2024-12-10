package com.example.project5bitfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project5bitfit.DataItems.NutritionItem
import com.example.project5bitfit.Views.NutritionAdapter

private const val TAG = "NutritionViewFragment"

class NutritionViewFragment : Fragment() {

    // Add these properties
    private val articles = mutableListOf<NutritionItem>()
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var articleAdapter: NutritionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_nutrition_view, container, false)
        val layoutManager = LinearLayoutManager(context)
        articlesRecyclerView = view.findViewById(R.id.article_recycler_view)
        articlesRecyclerView.layoutManager = layoutManager
        articlesRecyclerView.setHasFixedSize(true)
        articleAdapter = NutritionAdapter(view.context, articles)
        articlesRecyclerView.adapter = articleAdapter
        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String): NutritionViewFragment {
            return NutritionViewFragment()
        }

    }
}