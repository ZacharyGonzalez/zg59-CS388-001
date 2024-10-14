package com.example.joker

import com.google.gson.annotations.SerializedName

data class JokesResponse(
    @SerializedName("error") val error: Boolean,
    @SerializedName("amount") val amount: Int,
    @SerializedName("jokes") val jokes: List<Joke>
)
data class Joke(
    @SerializedName("id") val id: Int,
    @SerializedName("category") val category: String,
    @SerializedName("type") val type: String,
    @SerializedName("joke") val joke: String?,  // For "single" type jokes
    @SerializedName("flags") val flags: Flags,
    @SerializedName("safe") val safe: Boolean,
    @SerializedName("lang") val lang: String
)

data class Flags(
    @SerializedName("nsfw") val nsfw: Boolean,
    @SerializedName("religious") val religious: Boolean,
    @SerializedName("political") val political: Boolean,
    @SerializedName("racist") val racist: Boolean,
    @SerializedName("sexist") val sexist: Boolean,
    @SerializedName("explicit") val explicit: Boolean
)
