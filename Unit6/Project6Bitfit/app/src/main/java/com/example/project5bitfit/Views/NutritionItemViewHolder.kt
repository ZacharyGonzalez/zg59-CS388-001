package com.example.project5bitfit.Views

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project5bitfit.DataItems.NutritionItem
import com.example.project5bitfit.databinding.WishlistItemsBinding

class WishListItemViewHolder(
    //this is in-fact used in the adapter as the context for the view, don't know why it says it is not used.
    private val context: Context,
    private val binding: WishlistItemsBinding
): RecyclerView.ViewHolder(binding.root)
{
    val nameTextView : TextView = binding.nameItem
    val caloriesTextView : TextView = binding.caloriesItem
    val timeTextView : TextView = binding.timeItem

    fun bindWishListItem(wish: NutritionItem)
    {
        binding.nameItem.text = wish.name
        binding.caloriesItem.text = wish.calories
        binding.timeItem.text = wish.time
    }
}