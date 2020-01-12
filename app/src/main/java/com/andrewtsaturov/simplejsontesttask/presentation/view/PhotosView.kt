package com.andrewtsaturov.simplejsontesttask.presentation.view

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Photo
import com.arellomobile.mvp.MvpView

interface PhotosView: MvpView{
    fun showAlbum(album: Album)
    fun showPhotos(photos: List<Photo>)
    fun showFullImage(url: String)
}