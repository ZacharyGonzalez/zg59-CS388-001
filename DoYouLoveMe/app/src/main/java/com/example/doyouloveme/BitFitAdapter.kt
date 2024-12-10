package com.example.doyouloveme


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BitFitAdapter(
    private val items: List<BitFitItem>,
    private val onItemClicked: (BitFitItem) -> Unit
) : RecyclerView.Adapter<BitFitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.entry_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClicked(item) }
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemNameTextView: TextView = itemView.findViewById(R.id.ItemNameTextView)
        private val caloriesLabelTextView: TextView = itemView.findViewById(R.id.CaloriesLabel)

        fun bind(item: BitFitItem) {
            itemNameTextView.text = item.itemName

            // Truncate the calories(my card description) string to a maximum length (e.g., 20 characters)
            val maxLength = 30
            val truncatedCalories = if (item.calories.length > maxLength) {
                item.calories.substring(0, maxLength) + "..." // Add ellipsis if truncated
            } else {
                item.calories
            }

            caloriesLabelTextView.text = truncatedCalories
        }
    }
}

