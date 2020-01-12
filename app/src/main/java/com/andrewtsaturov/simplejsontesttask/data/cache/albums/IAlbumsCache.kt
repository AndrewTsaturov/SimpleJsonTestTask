package com.andrewtsaturov.simplejsontesttask.domain.repository

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import io.reactivex.Observable
import io.reactivex.Single

interface IAlbumsCache {
    fun observeAlbums(): Observable<List<Album>>
    fun updateAlbums(albums: List<Album>): Single<List<Album>>
    fun getAlbums(): List<Album>
}