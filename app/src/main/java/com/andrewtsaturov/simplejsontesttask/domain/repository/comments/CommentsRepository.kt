package com.andrewtsaturov.simplejsontesttask.domain.repository.comments

import com.andrewtsaturov.simplejsontesttask.data.api.mapper.toDomain
import com.andrewtsaturov.simplejsontesttask.data.api.service.comments.CommentsService
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import com.andrewtsaturov.simplejsontesttask.domain.repository.ICommentCache
import com.andrewtsaturov.simplejsontesttask.domain.repository.IPostsCache
import io.reactivex.Observable
import io.reactivex.Single

class CommentsRepository(
    private val service: CommentsService,
    private val commentsCache: ICommentCache,
    private val postCache: IPostsCache
): ICommentsRepository {
    override fun observePostForReading(): Observable<Post> = commentsCache.observePostForReading()

    override fun getPostById(id: Long): Single<Post> {
        var result = Post(0, 0, "", "")

        postCache.getPosts().map { if(it.id == id) result = it}

        return commentsCache.updatePost(result)
    }

    override fun observeComments(): Observable<List<Comment>> = commentsCache.observeComments()

    override fun getCommtensByPostId(id: Long): Single<List<Comment>> = service
        .getPosts(id)
        .map { it.map { it.toDomain() } }
        .flatMap { commentsCache.updateComments(it) }
}