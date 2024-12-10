package com.example.project5bitfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project5bitfit.DataBase.MyApplication
import com.example.project5bitfit.DataItems.NutritionModel
import com.example.project5bitfit.Views.NewWishList
import com.example.project5bitfit.Views.NutritionAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import com.example.project5bitfit.databinding.ActivityMainBinding as ActivityMainBinding1
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding1
    private lateinit var wishListViewModel: NutritionModel
    private lateinit var adapter: NutritionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding1.inflate(layoutInflater)
        setContentView(binding.root)

        wishListViewModel = ViewModelProvider(this)[NutritionModel::class.java]
        adapter = NutritionAdapter(mutableListOf())

        setRecyclerView()
        setupObservers()

        // Adding a new item
        binding.CreateEntry.setOnClickListener {
            NewWishList().show(supportFragmentManager, "NewWishList")
        }

        // Delete all items
        findViewById<Button>(R.id.DeleteAll).setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                (application as MyApplication).db.nutrientDao().deleteAll()
            }
            wishListViewModel.updateWishList(emptyList())
            Snackbar.make(binding.root, "All items deleted", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerView() {
        binding.rvWishList.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvWishList.adapter = adapter

        // Adding swipe gestures
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = adapter.wishes[position]
                lifecycleScope.launch(IO) {
                    (application as MyApplication).db.nutrientDao().delete(item.id)
                }
                adapter.wishes.removeAt(position)
                adapter.notifyItemRemoved(position)
                Snackbar.make(binding.root, "${item.name} deleted", Snackbar.LENGTH_SHORT).show()
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.rvWishList)
    }

    private fun setupObservers() {
        // Observe wishList changes
        wishListViewModel.wishList.observe(this) { updatedList ->
            adapter.wishes.clear()
            adapter.wishes.addAll(updatedList)
            adapter.notifyDataSetChanged()
        }

        // Populate initial list from database
        lifecycleScope.launch {
            (application as MyApplication).db.nutrientDao().getAll().collect { nutritionList ->
                wishListViewModel.updateWishList(nutritionList)
            }
        }
    }
}
