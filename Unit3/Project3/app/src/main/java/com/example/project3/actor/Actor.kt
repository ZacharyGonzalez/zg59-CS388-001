package com.example.project3.actor

import com.example.project3.movie.Movie
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Actor(
    @SerializedName("name") var title: String?,
    @SerializedName("profile_path") val actorImageUrl: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("known_for") val knownFor: List<Movie>,
    @SerializedName("overview") val overview: String?,
    @SerializedName("original_name") val original_name: String?

) : Serializable