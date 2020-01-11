package com.andrewtsaturov.simplejsontesttask.domain.interactor.comments

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import com.andrewtsaturov.simplejsontesttask.domain.repository.comments.ICommentsRepository
import io.reactivex.Observable
import io.reactivex.Single

class CommentsInteractor(private val repository: ICommentsRepository): ICommentsInteractor {
    override fun observePostForReading(): Observable<Post> = repository.observePostForReading()

    override fun getPostById(id: Long): Single<Post> = repository.getPostById(id)

    override fun observeComments(): Observable<List<Comment>> = repository.observeComments()

    override fun getCommtensByPostId(id: Long): Single<List<Comment>> = repository.getCommtensByPostId(id)
}