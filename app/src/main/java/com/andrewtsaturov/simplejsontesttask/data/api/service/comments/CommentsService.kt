package com.andrewtsaturov.simplejsontesttask.data.api.service.comments

import com.andrewtsaturov.simplejsontesttask.data.api.response.comments.CommentResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentsService {
    @GET("comments")
    fun getPosts(@Query("postId") postId: Long): Single<List<CommentResponse>>
}