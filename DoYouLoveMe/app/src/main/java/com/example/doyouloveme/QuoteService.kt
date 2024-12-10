package com.example.doyouloveme

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface QuoteService {

    @GET("lovequote")
    @Headers(
        "x-rapidapi-key: 6e45659ac8mshbe3dd609845de0bp17e974jsnd555dce064f2",
        "x-rapidapi-host: love-quote.p.rapidapi.com"
    )
    fun getQuote(): Call<QuoteResponse>
}


