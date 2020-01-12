package com.andrewtsaturov.simplejsontesttask.domain.interactor.albums

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import io.reactivex.Observable
import io.reactivex.Single

interface IAlbumsInteractor {
    fun observeAlbums(): Observable<List<Album>>
    fun loadAlbumsPage(pageNumber: Int): Single<List<Album>>
}