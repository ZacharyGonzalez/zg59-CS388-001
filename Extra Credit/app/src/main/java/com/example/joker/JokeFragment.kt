package com.example.joker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class JokeFragment : Fragment(), OnListFragmentInteractionListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: JokeRecyclerViewAdapter
    private val jokesList = mutableListOf<Joke>()
    private var isLoading = false
    private var currentPage = 1  // Track the current page for pagination

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_joke_list, container, false)
        val progressBar = view.findViewById<ContentLoadingProgressBar>(R.id.progress)
        recyclerView = view.findViewById<RecyclerView>(R.id.list)
        val context = view.context

        // Initialize the adapter
        adapter = JokeRecyclerViewAdapter(jokesList, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 1)

        // Load initial jokes
        fetchJokes(progressBar)

        // Add scroll listener
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    // User has scrolled to the bottom, fetch more jokes
                    fetchJokes(progressBar)
                }
            }
        })

        return view
    }

    private fun fetchJokes(progressBar: ContentLoadingProgressBar) {
        isLoading = true
        progressBar.show()
        val myApiKey: String = BuildConfig.API_KEY

        // Create OkHttpClient instance
        val client = OkHttpClient()

        // Build the request
        val request = Request.Builder()
            .url("https://jokeapi-v2.p.rapidapi.com/joke/Any?type=single&amount=10&page=$currentPage")  // Adjust the URL to fetch multiple jokes
            .get()
            .addHeader("x-rapidapi-key", myApiKey)  // Use API key from BuildConfig
            .addHeader("x-rapidapi-host", "jokeapi-v2.p.rapidapi.com")
            .build()

        // Execute the request
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                progressBar.hide()
                isLoading = false

                // Check for a successful response
                if (response.isSuccessful) {
                    // Parse JSON into JokesResponse
                    val responseBody = response.body?.string()
                    val gson = Gson()
                    val jokesResponse = gson.fromJson(responseBody, JokesResponse::class.java)

                    if (jokesResponse.jokes.isNotEmpty()) {
                        // Update the jokes list and notify the adapter
                        jokesList.addAll(jokesResponse.jokes)
                        activity?.runOnUiThread {
                            adapter.notifyDataSetChanged()
                        }
                        currentPage++  // Increment page for next API call
                    } else {
                        Log.e("JokeFragment", "No jokes found in response.")
                    }

                    // Log the successful response
                    Log.d("JokeFragment", "Response successful")
                    Log.d("JokeData", "JSON Response: $responseBody")
                } else {
                    Log.e("JokeFragment", "Response not successful: ${response.code} ${response.message}")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                progressBar.hide()
                isLoading = false
                Log.e("JokeFragment", "Error fetching jokes: ${e.message}", e)
            }
        })
    }

override fun onItemClick(item: Joke) {
    val jokeDetailFragment = JokeDetailFragment.newInstance(item.joke ?: "No joke available")
    requireActivity().supportFragmentManager.beginTransaction()
        .replace(R.id.content, jokeDetailFragment) // Make sure to use the correct container ID
        .addToBackStack(null)
        .commit()
}
}
