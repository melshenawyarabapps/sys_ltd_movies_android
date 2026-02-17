package com.movies.sys_ltd_movies_android.data.repository

import com.movies.sys_ltd_movies_android.data.remote.MovieApiService
import com.movies.sys_ltd_movies_android.data.remote.dto.toDomain
import com.movies.sys_ltd_movies_android.domain.model.Video
import com.movies.sys_ltd_movies_android.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService
) : MovieRepository {

    override suspend fun getMovieVideos(movieId: Int): List<Video> {
        return apiService.getMovieVideos(movieId).results.map { it.toDomain() }
    }
}
