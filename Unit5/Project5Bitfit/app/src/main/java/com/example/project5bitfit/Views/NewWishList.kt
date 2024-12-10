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
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class NewWishList : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewWishListBinding
    private lateinit var nutritionModel: NutritionModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nutritionModel = ViewModelProvider(requireActivity())[NutritionModel::class.java]
        binding.submitButton.setOnClickListener { saveAction() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNewWishListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction() {
        val name = binding.bitFitName.text.toString().trim()
        val calories = binding.calories.text.toString().trim()
        val time = binding.time.text.toString().trim()

        if (name.isEmpty() || calories.isEmpty() || time.isEmpty()) {
            Snackbar.make(binding.root, "All fields are required", Snackbar.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch(IO) {
            val db = (requireActivity().application as MyApplication).db
            db.nutrientDao().insert(NutritionEntity(name = name, calories = calories, time = time))

            // Update ViewModel
            val updatedList = db.nutrientDao().getAll().toList()
            nutritionModel.updateWishList(updatedList)
        }

        // Clear fields and dismiss the fragment
        binding.bitFitName.text?.clear()
        binding.calories.text?.clear()
        binding.time.text?.clear()
        dismiss()
    }
}
