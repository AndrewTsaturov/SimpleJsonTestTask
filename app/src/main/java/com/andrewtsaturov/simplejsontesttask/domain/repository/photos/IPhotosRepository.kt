package com.andrewtsaturov.simplejsontesttask.domain.repository.photos

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Photo
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import io.reactivex.Observable
import io.reactivex.Single

interface IPhotosRepository {
    fun observeAlbum(): Observable<Album>
    fun getAlbomById(id: Long): Single<Album>

    fun observePhotos(): Observable<List<Photo>>
    fun getPhotoByPostId(id: Long): Single<List<Photo>>
}