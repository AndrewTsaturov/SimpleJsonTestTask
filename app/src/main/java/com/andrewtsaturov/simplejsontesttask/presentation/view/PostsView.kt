package com.andrewtsaturov.simplejsontesttask.presentation.view

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import com.arellomobile.mvp.MvpView

interface PostsView: MvpView {
    fun updatePosts(posts: List<Post>)
}