package com.andrewtsaturov.simplejsontesttask.domain.repository.photos

import com.andrewtsaturov.simplejsontesttask.data.api.mapper.toDomain
import com.andrewtsaturov.simplejsontesttask.data.api.service.photos.PhotosService
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Photo
import com.andrewtsaturov.simplejsontesttask.domain.repository.IAlbumsCache
import com.andrewtsaturov.simplejsontesttask.domain.repository.IPhotosCache
import io.reactivex.Observable
import io.reactivex.Single

class PhotosRepository(
    private val service: PhotosService,
    private val albumsCache: IAlbumsCache,
    private val photosCache: IPhotosCache
    ): IPhotosRepository {

    override fun observeAlbum(): Observable<Album> = photosCache.observeAlbum()

    override fun getAlbomById(id: Long): Single<Album> {
        var result = Album(0, "")

        albumsCache.getAlbums().map { if(it.id == id) result = it}

        return photosCache.updateAlbum(result)
    }

    override fun observePhotos(): Observable<List<Photo>> = photosCache.observePhotos()

    override fun getPhotoByPostId(id: Long): Single<List<Photo>> = service
        .getPhotos(id)
        .map { it.map { it.toDomain() } }
        .flatMap { photosCache.updatePhotos(it) }
}