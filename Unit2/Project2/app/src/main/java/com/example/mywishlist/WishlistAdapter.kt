package com.example.mywishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(
    private val wishlistItems: MutableList<WishlistItem>,
    private val onItemDeleted:(Int) -> Unit
) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTV)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTV)
        val urlTextView: TextView = itemView.findViewById(R.id.urlTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int = wishlistItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = wishlistItems[position]
        holder.nameTextView.text = item.name
        holder.priceTextView.text = item.price
        holder.urlTextView.text = item.url

        //Stretch feature long click deletes item
        holder.itemView.setOnLongClickListener {
            onItemDeleted(position)
            true
        }
        // Stretch feature open URL function provided by CodePath Project 2
        holder.itemView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + item.name, Toast.LENGTH_LONG).show()
            }
        }
    }
}
