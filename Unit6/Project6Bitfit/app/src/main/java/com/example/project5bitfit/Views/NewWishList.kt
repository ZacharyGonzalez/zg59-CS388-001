package com.example.project5bitfit.Views
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.project5bitfit.DataBase.MyApplication
import com.example.project5bitfit.DataItems.NutritionEntity
import com.example.project5bitfit.DataItems.NutritionModel
import com.example.project5bitfit.databinding.FragmentNewWishListBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NewWishList : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentNewWishListBinding
    private lateinit var nutritionModel : NutritionModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        nutritionModel = ViewModelProvider(activity)[NutritionModel::class.java]
        binding.submitButton.setOnClickListener { saveAction() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNewWishListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction() {
        val name = binding.bitFitName.text.toString()
        val caloriesAmount = binding.calories.text.toString()
        val time = binding.time.text.toString()

        //Insert the data into the database
        lifecycleScope.launch(IO) {
            (requireActivity().application as MyApplication).db.nutrientDao().insert(
                NutritionEntity(name = name, calories = caloriesAmount, time = time)
            )
        }

        //Clear the fields of anything lingering
        binding.bitFitName.text?.clear()
        binding.calories.text?.clear()
        binding.time.text?.clear()

        //Removes the fragment display
        dismiss()
    }
}