package com.codepath.bestsellerlistapp

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.bestsellerlistapp.R.id

/**
 * [RecyclerView.Adapter] that can display a [BestSellerBook] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class BestSellerBooksRecyclerViewAdapter(
    private val books: List<BestSellerBook>,
    private val mListener: OnListFragmentInteractionListener?
    )
    : RecyclerView.Adapter<BestSellerBooksRecyclerViewAdapter.BookViewHolder>()
    {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_best_seller_book, parent, false)
        return BookViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: BestSellerBook? = null
        val mBookTitle: TextView = mView.findViewById<View>(id.book_title) as TextView
        val mBookAuthor: TextView = mView.findViewById<View>(id.book_author) as TextView
        val mBookDescription: TextView = mView.findViewById<View>(id.book_description) as TextView
        val mBookRank: TextView = mView.findViewById<View>(id.ranking) as TextView
        val mBookImage: ImageView = mView.findViewById<View>(id.book_image) as ImageView
        val mBookButton: Button = mView.findViewById<Button>(id.buy_button) as Button


        override fun toString(): String {
            return mBookTitle.toString() + " '" + mBookAuthor.text + "'"
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.mItem = book
        holder.mBookTitle.text = book.title
        holder.mBookAuthor.text = book.author
        holder.mBookDescription.text = book.description
        holder.mBookAuthor.text = book.author
        holder.mBookRank.text = book.rank.toString()
        Glide.with(holder.mView)
            .load(book.bookImageUrl)
            .centerInside()
            .into(holder.mBookImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }

        }
        //My Book Button has more functionality because i spent forever trouble shooting why the URL did not work, ad it was because of @serialized name being wrong for the Json Response
        holder.mBookButton.setOnClickListener {
            Log.d("BookAdapter", "Button clicked for book: ${book.title} with URL: ${book.amazonUrl}")
            if (!book.amazonUrl.isNullOrEmpty()) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(book.amazonUrl))
                startActivity(it.context, browserIntent, null)
            } else {
                Toast.makeText(it.context, "Invalid URL", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return books.size
    }
}