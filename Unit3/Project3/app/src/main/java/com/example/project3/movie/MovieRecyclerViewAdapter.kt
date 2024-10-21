package com.example.project3.movie

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project3.OnListFragmentInteractionListener
import com.example.project3.R

class MovieRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
    )
    : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>()
    {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }


    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(R.id.MovieBanner) as TextView
        val mOverview: TextView = mView.findViewById<View>(R.id.MovieOverview) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(R.id.movie_image) as ImageView
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        val URl= "https://image.tmdb.org/t/p/w500/" //this is the preface to what the json delivers us for a URL
        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mOverview.text = movie.overview

        // Check the orientation
        var image:String?=null
        val orientation = holder.mView.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            image = URl+movie.posterPath
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            image = URl+movie.backdropPath
        }

        Glide.with(holder.mView)
            .load(image)
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerInside()
            .into(holder.mMovieImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }

        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }
}