package com.andrewtsaturov.simplejsontesttask.data.api.service.posts

import com.andrewtsaturov.simplejsontesttask.data.api.response.posts.PostResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsService {
    @GET("posts")
    fun getPosts(@Query("_start") start: Int, @Query("_limit") limit: Int): Single<List<PostResponse>>
}