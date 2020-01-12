package com.andrewtsaturov.simplejsontesttask.data.api.mapper

import com.andrewtsaturov.simplejsontesttask.data.api.response.albums.AlbumResponse
import com.andrewtsaturov.simplejsontesttask.data.api.response.comments.CommentResponse
import com.andrewtsaturov.simplejsontesttask.data.api.response.photo.PhotoResponse
import com.andrewtsaturov.simplejsontesttask.data.api.response.posts.PostResponse
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Photo
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post

//In this project i have only four entities. So i have implemented mappers in this single file
//But in a real projects we should separate them by features or screens

fun PostResponse.toDomain() = Post(id, userId, title, body)

fun CommentResponse.toDomain() = Comment(id, name, email, body)

fun AlbumResponse.toDomain() = Album(id, title)

fun PhotoResponse.toDomain() = Photo(thumbnailUrl, url)