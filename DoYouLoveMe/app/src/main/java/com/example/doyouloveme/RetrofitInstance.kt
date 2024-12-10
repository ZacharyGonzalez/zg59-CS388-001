package com.example.doyouloveme

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getRetrofitInstance(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://love-quote.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create()) // Gson converter for JSON parsing
        .build()
}

fun getQuote() {
    val retrofit = getRetrofitInstance()
    val quoteService = retrofit.create(QuoteService::class.java)

    // Makes the network request asynchronously
    quoteService.getQuote().enqueue(object : Callback<QuoteResponse> {
        override fun onResponse(
            call: Call<QuoteResponse>,
            response: Response<QuoteResponse>
        ) {
            if (response.isSuccessful) {
                val quote = response.body()
                Log.d("QuoteService", "Quote: ${quote?.quote}, Author: ${quote?.author}")
            } else {
                Log.d("QuoteService", "API request failed with code: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
            Log.d("QuoteService", "API request failed: ${t.message}")
        }
    })
}

