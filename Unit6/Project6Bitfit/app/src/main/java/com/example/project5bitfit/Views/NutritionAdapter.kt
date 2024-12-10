package com.example.project5bitfit.Views

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project5bitfit.DataItems.NutritionItem
import com.example.project5bitfit.databinding.WishlistItemsBinding

class NutritionAdapter(
    private val context: Context,
    var articles: MutableList<NutritionItem> // Use a var so it can be updated
) : RecyclerView.Adapter<WishListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListItemViewHolder {
        val inflater = LayoutInflater.from(context)
        val wishView = WishlistItemsBinding.inflate(inflater, parent, false)
        return WishListItemViewHolder(context, wishView)
    }

    override fun onBindViewHolder(holder: WishListItemViewHolder, position: Int) {
        holder.bindWishListItem(articles[position])
        holder.nameTextView.setTypeface(holder.nameTextView.typeface, Typeface.BOLD)
        holder.caloriesTextView.setTypeface(holder.caloriesTextView.typeface, Typeface.BOLD)
        holder.timeTextView.setTypeface(holder.timeTextView.typeface, Typeface.BOLD)
    }

    override fun getItemCount(): Int = articles.size
}
