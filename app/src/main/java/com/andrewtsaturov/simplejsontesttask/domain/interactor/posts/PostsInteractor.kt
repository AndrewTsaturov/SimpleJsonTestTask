package com.andrewtsaturov.simplejsontesttask.domain.interactor.posts

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import com.andrewtsaturov.simplejsontesttask.domain.repository.posts.IPostsRepository
import io.reactivex.Observable
import io.reactivex.Single

class PostsInteractor(private val repository: IPostsRepository): IPostsInteractor {
    override fun observePosts(): Observable<List<Post>> = repository.observePosts()

    override fun loadPostPage(pageNumber: Int): Single<List<Post>> = repository.loadPostPage(pageNumber * 20)
}