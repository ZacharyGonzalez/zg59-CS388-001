package com.example.project3.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.project3.actor.Actor
import com.example.project3.BuildConfig
import com.example.project3.OnListFragmentInteractionListener
import com.example.project3.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private var API_KEY = BuildConfig.API_KEY

class MovieFragment : Fragment(), OnListFragmentInteractionListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        val progressBar = view.findViewById<ContentLoadingProgressBar>(R.id.MovieProgress)
        val recyclerView = view.findViewById<RecyclerView>(R.id.MovieHorizontalRecyclerView)
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        updateAdapter(progressBar, recyclerView)
        return view
    }


    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        // Perform the HTTP request
        client.get(
            "https://api.themoviedb.org/3/movie/popular", // Correct endpoint for popular movies
            params,
            object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    // Parse JSON into Models
                    val resultsJSON: JSONArray = json.jsonObject.getJSONArray("results") // Use getJSONArray
                    val gson = Gson()
                    val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
                    // Convert JSON array to list of Movie objects
                    val models: List<Movie> = gson.fromJson(resultsJSON.toString(), arrayMovieType)
                    recyclerView.adapter = MovieRecyclerViewAdapter(models, this@MovieFragment)

                    // Log the successful response
                    Log.d("MovieFragment", "Response successful")
                    Log.d("MovieData", "JSON Response: $resultsJSON")
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    // Log the error
                    Log.e("MovieFragment", "Error fetching movies: $errorResponse", t)
                }
            }
        )
    }
    private var selectedItem: Any? = null // To hold the currently selected item
    // Implement the onItemClick methods from the listener
    override fun onItemClick(movie: Movie) {
        Toast.makeText(context, "Clicked Movie: ${movie.title}", Toast.LENGTH_LONG).show()
        selectedItem = movie // Store the selected item
    }

    override fun onItemClick(actor: Actor) {
        Toast.makeText(context, "Clicked Actor: ${actor.title}", Toast.LENGTH_LONG).show()
        selectedItem = actor // Store the selected item
    }

    // Optional method to get the current item
    override fun getCurrentItem(): Any? {
        return selectedItem
    }
}
