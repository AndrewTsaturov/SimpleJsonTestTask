package com.andrewtsaturov.simplejsontesttask.data.cache.posts

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import com.andrewtsaturov.simplejsontesttask.domain.repository.IPostsCache
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.Single

class PostsCache: IPostsCache {

    private val relay = BehaviorRelay.create<List<Post>>()

    override fun observePosts(): Observable<List<Post>> = relay.hide()

    override fun updatePostPage(posts: List<Post>): Single<List<Post>> = Single.fromCallable {
        val buffer = mutableListOf<Post>()
        if(!relay.value.isNullOrEmpty())
            buffer.addAll(relay.value!!)

        buffer.addAll(posts)

        relay.accept(buffer)
        buffer
    }

    override fun getPosts(): List<Post> = relay.value ?: mutableListOf()

}