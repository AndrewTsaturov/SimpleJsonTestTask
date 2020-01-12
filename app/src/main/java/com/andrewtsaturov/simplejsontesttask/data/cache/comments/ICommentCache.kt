package com.andrewtsaturov.simplejsontesttask.domain.repository

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import io.reactivex.Observable
import io.reactivex.Single

interface ICommentCache {
    fun observePostForReading(): Observable<Post>
    fun updatePost(post: Post): Single<Post>

    fun observeComments(): Observable<List<Comment>>
    fun updateComments(comments: List<Comment>): Single<List<Comment>>
}