package com.example.joker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
class JokeRecyclerViewAdapter(
    private val jokes: MutableList<Joke>, // Change to MutableList
    private val listener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<JokeRecyclerViewAdapter.JokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.frag_joke, parent, false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = jokes[position]
        holder.jokeTextView.text = joke.joke
        // Set alternating colors
        holder.itemView.setBackgroundColor(if (position % 2 == 0) Color.parseColor("#E0E0E0") else Color.parseColor("#FFFFFF"))

        holder.itemView.setOnClickListener {
            listener?.onItemClick(joke)
        }
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    // Add this method to append jokes
    fun addJokes(newJokes: List<Joke>) {
        val startPosition = jokes.size
        jokes.addAll(newJokes)
        notifyItemRangeInserted(startPosition, newJokes.size)
    }

    inner class JokeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val jokeTextView: TextView = view.findViewById(R.id.overview)
    }
}
