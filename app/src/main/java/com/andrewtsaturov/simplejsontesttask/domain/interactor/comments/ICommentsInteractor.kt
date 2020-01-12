package com.andrewtsaturov.simplejsontesttask.domain.interactor.comments

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import io.reactivex.Observable
import io.reactivex.Single

interface ICommentsInteractor {
    fun observePostForReading(): Observable<Post>
    fun getPostById(id: Long): Single<Post>

    fun observeComments(): Observable<List<Comment>>
    fun getCommtensByPostId(id: Long): Single<List<Comment>>
}