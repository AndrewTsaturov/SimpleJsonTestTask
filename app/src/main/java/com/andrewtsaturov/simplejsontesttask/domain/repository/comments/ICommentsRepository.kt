package com.andrewtsaturov.simplejsontesttask.domain.repository.comments

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import io.reactivex.Observable
import io.reactivex.Single

interface ICommentsRepository {
    fun observePostForReading(): Observable<Post>
    fun getPostById(id: Long): Single<Post>

    fun observeComments(): Observable<List<Comment>>
    fun getCommtensByPostId(id: Long): Single<List<Comment>>
}