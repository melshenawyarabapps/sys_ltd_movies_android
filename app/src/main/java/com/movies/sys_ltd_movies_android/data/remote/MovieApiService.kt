package com.movies.sys_ltd_movies_android.data.remote

import com.movies.sys_ltd_movies_android.BuildConfig
import com.movies.sys_ltd_movies_android.data.remote.dto.VideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): VideosResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}
