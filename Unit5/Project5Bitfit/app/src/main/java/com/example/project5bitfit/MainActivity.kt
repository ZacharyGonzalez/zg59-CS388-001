package com.example.project5bitfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project5bitfit.DataBase.MyApplication
import com.example.project5bitfit.DataItems.NutritionModel
import com.example.project5bitfit.Views.NewWishList
import com.example.project5bitfit.Views.NutritionAdapter
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import com.example.project5bitfit.databinding.ActivityMainBinding as ActivityMainBinding1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding1
    private lateinit var wishListViewModel: NutritionModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding1.inflate(layoutInflater)
        setContentView(binding.root)
        wishListViewModel = ViewModelProvider(this)[NutritionModel::class.java]
        binding.CreateEntry.setOnClickListener {
            NewWishList().show(supportFragmentManager, "NewWishList")
        }

        setRecyclerView()

        //Press button to delete and update the list
        findViewById<Button>(R.id.DeleteAll).setOnClickListener {
            lifecycleScope.launch(IO) {
                (application as MyApplication).db.nutrientDao().deleteAll()
            }
            wishListViewModel.updateWishList(emptyList())
        }

    }

    private fun setRecyclerView() {
        binding.rvWishList.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = NutritionAdapter(mutableListOf()) // Initially empty
        }
        //Observe the database and update our ViewModel (which is from the wishlist project)
        lifecycleScope.launch {
            (application as MyApplication).db.nutrientDao().getAll().collect { nutritionList ->
                wishListViewModel.updateWishList(nutritionList)
                (binding.rvWishList.adapter as NutritionAdapter).apply {
                    this.wishes.clear()
                    this.wishes.addAll(wishListViewModel.wishList.value ?: listOf())
                    notifyDataSetChanged()
                }
            }
        }
    }
}