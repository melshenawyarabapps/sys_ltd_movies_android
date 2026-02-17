package com.movies.sys_ltd_movies_android.domain.usecase

import com.movies.sys_ltd_movies_android.domain.model.Video
import com.movies.sys_ltd_movies_android.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieTrailerUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Video? {
        val videos = repository.getMovieVideos(movieId)
        return videos.firstOrNull { it.site == "YouTube" && it.type == "Trailer" }
    }
}
