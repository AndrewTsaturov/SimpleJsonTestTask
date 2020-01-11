package com.andrewtsaturov.simplejsontesttask.domain.repository

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Photo
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import io.reactivex.Observable
import io.reactivex.Single

interface IPhotosCache {
    fun observeAlbum(): Observable<Album>
    fun updateAlbum(album: Album): Single<Album>

    fun observePhotos(): Observable<List<Photo>>
    fun updatePhotos(photos: List<Photo>): Single<List<Photo>>
}