package com.andrewtsaturov.simplejsontesttask.domain.interactor.posts

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import io.reactivex.Observable
import io.reactivex.Single

interface IPostsInteractor {
    fun observePosts(): Observable<List<Post>>
    fun loadPostPage(pageNumber: Int): Single<List<Post>>
}