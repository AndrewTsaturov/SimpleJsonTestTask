package com.andrewtsaturov.simplejsontesttask.domain.repository

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import io.reactivex.Observable
import io.reactivex.Single

interface IPostsCache {
    fun observePosts(): Observable<List<Post>>
    fun updatePostPage(posts: List<Post>): Single<List<Post>>
    fun getPosts(): List<Post>
}