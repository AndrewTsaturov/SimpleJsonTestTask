package com.andrewtsaturov.simplejsontesttask.data.api.service.photos

import com.andrewtsaturov.simplejsontesttask.data.api.response.comments.CommentResponse
import com.andrewtsaturov.simplejsontesttask.data.api.response.photo.PhotoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosService {
    @GET("photos")
    fun getPosts(@Query("albumId") postId: Long): Single<List<PhotoResponse>>
}