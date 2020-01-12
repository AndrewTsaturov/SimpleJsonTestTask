package com.andrewtsaturov.simplejsontesttask.presentation.screen.comments.adapter

import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentsItem(private val comment: Comment): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            itemCommentNameTxt.text = comment.name
            itemCommentEmailTxt.text = comment.eMail
            itemCommentBodyTxt.text = comment.body
        }
    }

    override fun getLayout(): Int = R.layout.item_comment
}