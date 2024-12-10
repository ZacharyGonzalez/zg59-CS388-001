package com.example.project5bitfit.DataItems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NutritionModel : ViewModel() {
    private val _wishList = MutableLiveData<List<NutritionItem>>()
    val wishList: LiveData<List<NutritionItem>> get() = _wishList

    fun updateWishList(newList: List<NutritionEntity>) {
        _wishList.postValue(newList)
    }
}
