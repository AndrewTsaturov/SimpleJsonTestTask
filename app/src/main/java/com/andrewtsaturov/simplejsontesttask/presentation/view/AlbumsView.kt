package com.andrewtsaturov.simplejsontesttask.presentation.view

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.arellomobile.mvp.MvpView

interface AlbumsView: MvpView {
    fun updateAlbums(albums: List<Album>)
}