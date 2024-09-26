package com.example.mywishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WishlistAdapter
    private val itemList = mutableListOf<WishlistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.Wishlist)
        adapter = WishlistAdapter(itemList){ position ->
            if (position >= 0 && position < itemList.size){ //To delete the first entry in the list and make sure position is right.
            itemList.removeAt(position)
            adapter.notifyItemRemoved(position)
            adapter.notifyItemRangeChanged(position, itemList.size)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val nameInput: EditText = findViewById(R.id.nameTV)
        val priceInput: EditText = findViewById(R.id.priceTV)
        val urlInput: EditText = findViewById(R.id.url)

        findViewById<Button>(R.id.submit).setOnClickListener {
            val name = nameInput.text.toString()
            val price = priceInput.text.toString()
            val url = urlInput.text.toString()

            // Basic input validation
            if (name.isNotBlank() && price.isNotBlank() && url.isNotBlank()) {
                val newItem = WishlistItem(name, price, url)
                itemList.add(newItem)
                adapter.notifyItemInserted(itemList.size)
                nameInput.text.clear()
                priceInput.text.clear()
                urlInput.text.clear()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
