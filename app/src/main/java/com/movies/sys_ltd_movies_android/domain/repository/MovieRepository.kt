package com.movies.sys_ltd_movies_android.domain.repository

import com.movies.sys_ltd_movies_android.domain.model.Video

interface MovieRepository {
    suspend fun getMovieVideos(movieId: Int): List<Video>
}
