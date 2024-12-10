package com.example.doyouloveme

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardFragment : Fragment() {

    private lateinit var clearDataButton: Button
    private lateinit var signOutButton: Button
    private lateinit var themeRadioGroup: RadioGroup
    private lateinit var lightModeButton: Button
    private lateinit var darkModeButton: Button

    private val items = mutableListOf<BitFitItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        clearDataButton = view.findViewById(R.id.ClearDataButton)
        signOutButton = view.findViewById(R.id.SignOutButton)
        themeRadioGroup = view.findViewById(R.id.themeRadioGroup)
        lightModeButton = view.findViewById(R.id.lightModeButton)
        darkModeButton = view.findViewById(R.id.darkModeButton)

        // Theme settings handler
        val sharedPreferences = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val currentTheme = sharedPreferences.getString("theme_preference", "light")

        // Set the current selected theme based on saved preference
        when (currentTheme) {
            "light" -> themeRadioGroup.check(R.id.lightModeButton)
            "dark" -> themeRadioGroup.check(R.id.darkModeButton)
        }

        themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val themePreference = when (checkedId) {
                R.id.lightModeButton -> "light"
                R.id.darkModeButton -> "dark"
                else -> "light"
            }
            sharedPreferences.edit().putString("theme_preference", themePreference).apply()
            setAppTheme(themePreference)
        }

        lightModeButton.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // Switch to light mode
            sharedPreferences.edit().putString("theme_preference", "light").apply() // Save preference
        }

        darkModeButton.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) // Switch to dark mode
            sharedPreferences.edit().putString("theme_preference", "dark").apply() // Save preference
        }

        clearDataButton.setOnClickListener {
            lifecycleScope.launch(IO) {
                (activity?.application as BitFitApplication).db.bitfitDao().deleteAll()
            }
        }

        signOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            activity?.finish()
        }

        // Collect data from the database
        lifecycleScope.launch(IO) {
            (activity?.application as BitFitApplication).db.bitfitDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    BitFitItem(entity.itemName.toString(), entity.calories.toString()).also {
                    }
                }.also { mappedList ->
                    items.clear()
                    items.addAll(mappedList)
                }
            }
        }
    }

    private fun setAppTheme(theme: String?) {
        when (theme) {
            "light" -> activity?.setTheme(R.style.AppTheme_Light)
            "dark" -> activity?.setTheme(R.style.AppTheme_Dark)
        }
        requireActivity().recreate() // Recreate the activity to apply the new theme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }
}
