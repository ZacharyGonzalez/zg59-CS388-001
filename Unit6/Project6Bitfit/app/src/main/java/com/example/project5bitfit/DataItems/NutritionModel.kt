package com.example.project5bitfit.DataItems

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NutritionModel: ViewModel() {
    var wishList = MutableLiveData<MutableList<NutritionItem>>()
    init {
        wishList.value = mutableListOf()
    }

    //Declaring it a mutable-list breaks other functions so we do this transition to be able to live update the data.
    fun updateWishList(nutritionList: List<NutritionEntity>) {

        val newWishList = nutritionList.map { entity -> NutritionItem(
                name = entity.name,
                calories = entity.calories,
                time = entity.time)
        }.toMutableList()

        wishList.value = newWishList
        wishList.postValue(newWishList) //Notify our observer of the change
    }

}
