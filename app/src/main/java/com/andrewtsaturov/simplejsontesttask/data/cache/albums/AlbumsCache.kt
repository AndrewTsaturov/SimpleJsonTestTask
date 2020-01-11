package com.andrewtsaturov.simplejsontesttask.data.cache.albums

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.repository.IAlbumsCache
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.Single

class AlbumsCache: IAlbumsCache {
    private val relay = BehaviorRelay.create<List<Album>>()

    override fun observeAlbums(): Observable<List<Album>> = relay.hide()

    override fun updateAlbums(albums: List<Album>): Single<List<Album>> = Single.fromCallable {
        val buffer = mutableListOf<Album>()
        if(!relay.value.isNullOrEmpty())
            buffer.addAll(relay.value!!)

        buffer.addAll(albums)

        relay.accept(buffer)
        buffer
    }

    override fun getAlbums(): List<Album> = relay.value ?: mutableListOf()
}