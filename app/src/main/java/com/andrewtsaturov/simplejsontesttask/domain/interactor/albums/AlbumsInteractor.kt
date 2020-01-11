package com.andrewtsaturov.simplejsontesttask.domain.interactor.albums

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.repository.albums.IAlbumsRepository
import io.reactivex.Observable
import io.reactivex.Single

class AlbumsInteractor(private val repository: IAlbumsRepository): IAlbumsInteractor {
    override fun observeAlbums(): Observable<List<Album>> = repository.observeAlbums()

    override fun loadAlbumsPage(pageNumber: Int): Single<List<Album>> = repository.loadAlbums(pageNumber * 20)
}