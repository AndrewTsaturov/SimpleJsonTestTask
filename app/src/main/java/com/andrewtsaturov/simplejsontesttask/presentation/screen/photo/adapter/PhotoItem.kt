package com.andrewtsaturov.simplejsontesttask.presentation.screen.photo.adapter

import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Photo
import com.andrewtsaturov.simplejsontesttask.presentation.screen.photo.OnPhotoClickListener
import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoItem(
    private val photo: Photo,
    private val listener: OnPhotoClickListener
): Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            Glide.with(context).load(photo.preview).into(itemPhotoImg)
            setOnClickListener { listener.onPhotoClicked(photo.full) }
        }
    }

    override fun getLayout(): Int = R.layout.item_photo

}