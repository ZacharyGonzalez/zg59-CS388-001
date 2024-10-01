package com.example.project3

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single book from the NY Times API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Movie {
   // @SerializedName("rank")
  //  var rank = 0

    @JvmField
    @SerializedName("title")
    var title: String? = null

    //@JvmField
    //@SerializedName("author")
   // var author: String? = null

    //TODO bookImageUrl
    @SerializedName("poster_path")
    val movieImageUrl: String?= null

    //TODO description
    @SerializedName("overview")
    val overview: String?= null

    //TODO-STRETCH-GOALS amazonUrl
    //I had to read the Json Response to figure out that its 'amazon_product_url:' not 'amazonUrl:'
   // @SerializedName("amazon_product_url")
    //val amazonUrl: String?= null
}