package com.andrewtsaturov.simplejsontesttask.data.cache.comments

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import com.andrewtsaturov.simplejsontesttask.domain.repository.ICommentCache
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.Single

class CommentsCache: ICommentCache {

    private val postRelay = BehaviorRelay.create<Post>()
    private val commentsRelay = BehaviorRelay.create<List<Comment>>()

    override fun observePostForReading(): Observable<Post> = postRelay.hide()

    override fun updatePost(post: Post): Single<Post> = Single.fromCallable {
        postRelay.accept(post)
        post
    }

    override fun observeComments(): Observable<List<Comment>> = commentsRelay.hide()

    override fun updateComments(comments: List<Comment>): Single<List<Comment>> = Single.fromCallable {
        commentsRelay.accept(comments)
        comments
    }
}