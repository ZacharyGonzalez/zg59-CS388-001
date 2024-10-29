package com.example.project3.actor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.project3.BuildConfig
import com.example.project3.movie.Movie
import com.example.project3.OnListFragmentInteractionListener
import com.example.project3.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private var API_KEY = BuildConfig.API_KEY

class ActorFragment : Fragment(), OnListFragmentInteractionListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_actor_list, container, false)
        val progressBar = view.findViewById<ContentLoadingProgressBar>(R.id.ActorProgress)
        val recyclerView = view.findViewById<RecyclerView>(R.id.ActorHorizontalRecyclerView)
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context,2)
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
            "https://api.themoviedb.org/3/person/popular", // Correct endpoint for popular Actors
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
                    val arrayActorType = object : TypeToken<List<Actor>>() {}.type
                    // Convert JSON array to list of Actor objects
                    val models: List<Actor> = gson.fromJson(resultsJSON.toString(), arrayActorType)
                    recyclerView.adapter = ActorRecyclerViewAdapter(models, this@ActorFragment)

                    // Log the successful response
                    Log.d("ActorFragment", "Response successful")
                    Log.d("ActorData", "JSON Response: $resultsJSON")
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
                    Log.e("ActorFragment", "Error fetching Actors: $errorResponse", t)
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
        val intent = Intent(context, ActorDetail::class.java)
        intent.putExtra(ACTOR_EXTRA, actor) // Pass Actor object via intent
        startActivity(intent)
    }


    // Optional method to get the current item
    override fun getCurrentItem(): Any? {
        return selectedItem
    }
}
