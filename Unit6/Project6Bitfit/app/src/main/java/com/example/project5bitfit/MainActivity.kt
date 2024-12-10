package com.example.project5bitfit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.project5bitfit.DataItems.NutritionModel
import com.example.project5bitfit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var wishListViewModel: NutritionModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Use an activity layout as the container

        // Initialize the ViewModel
        wishListViewModel = ViewModelProvider(this)[NutritionModel::class.java]

        // Add or replace the DashboardFragment in the container
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DashboardFragment()) // Replace with your fragment container
                .commit()
        }
    }
}

