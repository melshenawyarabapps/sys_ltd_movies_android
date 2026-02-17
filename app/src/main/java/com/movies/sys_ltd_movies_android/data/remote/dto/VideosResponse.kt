package com.movies.sys_ltd_movies_android.data.remote.dto

import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<VideoDto>
)
