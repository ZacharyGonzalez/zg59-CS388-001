package com.example.project3

import com.google.gson.annotations.SerializedName

class Movie {

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @SerializedName("poster_path")
    val movieImageUrl: String?= null

    @SerializedName("backdrop_path")
    val landscapeimage: String?=null

    @SerializedName("overview")
    val overview: String?= null

}