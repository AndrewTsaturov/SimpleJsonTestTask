package com.andrewtsaturov.simplejsontesttask.presentation.view

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import com.arellomobile.mvp.MvpView

interface CommentsView: MvpView{
    fun showPost(post: Post)
    fun showComments(comments: List<Comment>)
}