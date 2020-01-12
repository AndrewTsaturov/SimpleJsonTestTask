package com.andrewtsaturov.simplejsontesttask.domain.repository.albums

import com.andrewtsaturov.simplejsontesttask.data.api.mapper.toDomain
import com.andrewtsaturov.simplejsontesttask.data.api.service.albums.AlbumsService
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.repository.IAlbumsCache
import io.reactivex.Observable
import io.reactivex.Single

class AlbumsRepository(
    private val service: AlbumsService,
    private val cache: IAlbumsCache
): IAlbumsRepository {
    override fun observeAlbums(): Observable<List<Album>> = cache.observeAlbums()

    override fun loadAlbums(from: Int): Single<List<Album>> = service
        .getPosts(from, 20)
        .map { it.map { it.toDomain() } }
        .flatMap { cache.updateAlbums(it) }
}