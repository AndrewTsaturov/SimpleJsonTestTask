package com.andrewtsaturov.simplejsontesttask.data.cache.photos

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Photo
import com.andrewtsaturov.simplejsontesttask.domain.repository.IPhotosCache
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.Single

class PhotosCache: IPhotosCache {

    private val albumRelay = BehaviorRelay.create<Album>()
    private val photosRelay = BehaviorRelay.create<List<Photo>>()

    override fun observeAlbum(): Observable<Album> = albumRelay.hide()

    override fun updateAlbum(album: Album): Single<Album> = Single.fromCallable {
        albumRelay.accept(album)
        album
    }

    override fun observePhotos(): Observable<List<Photo>> = photosRelay.hide()

    override fun updatePhotos(photos: List<Photo>): Single<List<Photo>> = Single.fromCallable {
        photosRelay.accept(photos)
        photos
    }
}