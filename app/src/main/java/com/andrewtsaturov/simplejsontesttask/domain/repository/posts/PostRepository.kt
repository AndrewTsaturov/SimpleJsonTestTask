package com.andrewtsaturov.simplejsontesttask.domain.repository.posts

import com.andrewtsaturov.simplejsontesttask.data.api.mapper.toDomain
import com.andrewtsaturov.simplejsontesttask.data.api.service.posts.PostsService
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import com.andrewtsaturov.simplejsontesttask.domain.repository.IPostsCache
import io.reactivex.Observable
import io.reactivex.Single

class PostRepository(
    private val service: PostsService,
    private val cache: IPostsCache
): IPostsRepository {

    override fun observePosts(): Observable<List<Post>> = cache.observePosts()

    override fun loadPostPage(from: Int): Single<List<Post>> = service.getPosts(from, 20)
        .map { it.map { it.toDomain() } }
        .flatMap { cache.updatePostPage(it) }
}