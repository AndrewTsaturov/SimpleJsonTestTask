package com.andrewtsaturov.simplejsontesttask.domain.interactor.photos

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Photo
import com.andrewtsaturov.simplejsontesttask.domain.repository.photos.IPhotosRepository
import io.reactivex.Observable
import io.reactivex.Single

class PhotosInteractor(private val repository: IPhotosRepository): IPhotosInteractor {

    override fun observeAlbum(): Observable<Album> = repository.observeAlbum()

    override fun getAlbomById(id: Long): Single<Album> = repository.getAlbomById(id)

    override fun observePhotos(): Observable<List<Photo>> = repository.observePhotos()

    override fun getPhotoByPostId(id: Long): Single<List<Photo>> = repository.getPhotoByPostId(id)
}