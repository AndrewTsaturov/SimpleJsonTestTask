package com.andrewtsaturov.simplejsontesttask.data.api.service.albums

import com.andrewtsaturov.simplejsontesttask.data.api.response.albums.AlbumResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumsService {
    @GET("albums")
    fun getPosts(@Query("_start") start: Int, @Query("_limit") limit: Int): Single<List<AlbumResponse>>
}