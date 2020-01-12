package com.andrewtsaturov.simplejsontesttask.presentation.screen.albums.adapter

import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.presentation.screen.albums.OnAlbumsClickListener
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_albums.view.*

class AlbumItem(
    private val album: Album,
    private val listener: OnAlbumsClickListener): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.apply {
            itemAlbumsHeaderTxt.text = album.name
            setOnClickListener { listener.onAlbumClick(album.id) }
        }
    }

    override fun getLayout(): Int = R.layout.item_albums

}