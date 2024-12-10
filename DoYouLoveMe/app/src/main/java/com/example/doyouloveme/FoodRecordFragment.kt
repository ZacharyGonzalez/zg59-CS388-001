package com.example.doyouloveme


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class FoodRecordFragment : Fragment() {

    private val items = mutableListOf<BitFitItem>()
    private lateinit var itemAdapter: BitFitAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchItems()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_record, container, false)

        val layoutManager = LinearLayoutManager(context)
        val itemRecyclerView = view.findViewById<RecyclerView>(R.id.ItemsListRecyclerView)
        itemRecyclerView.layoutManager = layoutManager
        itemRecyclerView.setHasFixedSize(true)

        itemAdapter = BitFitAdapter(items) { clickedItem ->
            onItemClicked(clickedItem)
        }

        itemRecyclerView.adapter = itemAdapter
        return view
    }

    private fun fetchItems() {
        lifecycleScope.launch {
            (activity?.application as BitFitApplication).db.bitfitDao().getAll().collect { databaseList ->
                val mappedList = databaseList.map { entity ->
                    BitFitItem(entity.itemName.toString(), entity.calories.toString())
                }
                items.clear()
                items.addAll(mappedList)
                itemAdapter.notifyDataSetChanged()
            }
        }
    }
    // Replace the fragment in the container to detail view
    private fun onItemClicked(item: BitFitItem) {
        val detailsFragment = DetailsFragment.newInstance(item)
        parentFragmentManager.beginTransaction()
            .replace(R.id.content, detailsFragment)
            .addToBackStack(null)
            .commit()
    }
}
