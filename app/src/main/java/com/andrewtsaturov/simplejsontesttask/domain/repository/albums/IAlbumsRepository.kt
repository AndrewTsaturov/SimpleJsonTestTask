package com.andrewtsaturov.simplejsontesttask.domain.repository.albums

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import io.reactivex.Observable
import io.reactivex.Single

interface IAlbumsRepository {
    fun observeAlbums(): Observable<List<Album>>
    fun loadAlbums(from: Int): Single<List<Album>>
}