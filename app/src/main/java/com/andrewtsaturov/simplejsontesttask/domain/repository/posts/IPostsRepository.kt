package com.andrewtsaturov.simplejsontesttask.domain.repository.posts

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import io.reactivex.Observable
import io.reactivex.Single

interface IPostsRepository {
    fun observePosts(): Observable<List<Post>>
    fun loadPostPage(from: Int): Single<List<Post>>
}