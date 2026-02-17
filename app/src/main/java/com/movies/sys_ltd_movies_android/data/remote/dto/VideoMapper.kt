package com.movies.sys_ltd_movies_android.data.remote.dto

import com.movies.sys_ltd_movies_android.domain.model.Video

fun VideoDto.toDomain(): Video {
    return Video(
        id = id,
        key = key,
        name = name,
        site = site,
        type = type
    )
}
