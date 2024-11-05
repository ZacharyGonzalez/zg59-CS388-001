package com.example.project5bitfit.Views

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project5bitfit.DataItems.NutritionItem
import com.example.project5bitfit.databinding.WishlistItemsBinding


class NutritionAdapter(val wishes: MutableList<NutritionItem>) : RecyclerView.Adapter<WishListItemViewHolder>() {

    //Set up the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListItemViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val wishView = WishlistItemsBinding.inflate(inflater, parent, false)
        return WishListItemViewHolder(context, wishView)
    }

    //Populate the views
    override fun onBindViewHolder(holder: WishListItemViewHolder, position: Int) {
        holder.bindWishListItem(wishes[position])
        holder.nameTextView.setTypeface(holder.nameTextView.typeface, Typeface.BOLD)
        holder.caloriesTextView.setTypeface(holder.caloriesTextView.typeface, Typeface.BOLD)
        holder.timeTextView.setTypeface(holder.timeTextView.typeface, Typeface.BOLD)
    }

    override fun getItemCount(): Int = wishes.size
}