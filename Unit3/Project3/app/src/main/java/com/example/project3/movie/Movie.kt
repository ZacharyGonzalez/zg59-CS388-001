package com.example.project3.movie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?, // Some are movies with title
    @SerializedName("original_title") val originalTitle: String?, // Korean titles are original titles
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("popularity") val popularity: Double?

) : Serializable

