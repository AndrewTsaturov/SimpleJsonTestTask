package com.andrewtsaturov.simplejsontesttask.presentation.screen.posts.adapter

import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import com.andrewtsaturov.simplejsontesttask.presentation.screen.posts.OnPostClickListener
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_post.view.*


class PostItem(
    private val post: Post,
    private val listener: OnPostClickListener
): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            setOnClickListener { listener.onPostClick(post.id) }
            itemPostHeaderTxt.text = post.header
            itemPostBodyTxt.text = post.body
        }
    }

    override fun getLayout(): Int = R.layout.item_post
}