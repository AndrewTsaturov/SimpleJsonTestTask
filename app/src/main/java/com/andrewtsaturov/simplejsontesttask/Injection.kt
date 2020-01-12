package com.andrewtsaturov.simplejsontesttask

import com.andrewtsaturov.simplejsontesttask.data.api.service.albums.AlbumsService
import com.andrewtsaturov.simplejsontesttask.data.api.service.comments.CommentsService
import com.andrewtsaturov.simplejsontesttask.data.api.service.photos.PhotosService
import com.andrewtsaturov.simplejsontesttask.data.api.service.posts.PostsService
import com.andrewtsaturov.simplejsontesttask.data.cache.albums.AlbumsCache
import com.andrewtsaturov.simplejsontesttask.data.cache.comments.CommentsCache
import com.andrewtsaturov.simplejsontesttask.data.cache.photos.PhotosCache
import com.andrewtsaturov.simplejsontesttask.data.cache.posts.PostsCache
import com.andrewtsaturov.simplejsontesttask.domain.interactor.albums.AlbumsInteractor
import com.andrewtsaturov.simplejsontesttask.domain.interactor.albums.IAlbumsInteractor
import com.andrewtsaturov.simplejsontesttask.domain.interactor.comments.CommentsInteractor
import com.andrewtsaturov.simplejsontesttask.domain.interactor.comments.ICommentsInteractor
import com.andrewtsaturov.simplejsontesttask.domain.interactor.photos.IPhotosInteractor
import com.andrewtsaturov.simplejsontesttask.domain.interactor.photos.PhotosInteractor
import com.andrewtsaturov.simplejsontesttask.domain.interactor.posts.IPostsInteractor
import com.andrewtsaturov.simplejsontesttask.domain.interactor.posts.PostsInteractor
import com.andrewtsaturov.simplejsontesttask.domain.repository.IAlbumsCache
import com.andrewtsaturov.simplejsontesttask.domain.repository.ICommentCache
import com.andrewtsaturov.simplejsontesttask.domain.repository.IPhotosCache
import com.andrewtsaturov.simplejsontesttask.domain.repository.IPostsCache
import com.andrewtsaturov.simplejsontesttask.domain.repository.albums.AlbumsRepository
import com.andrewtsaturov.simplejsontesttask.domain.repository.albums.IAlbumsRepository
import com.andrewtsaturov.simplejsontesttask.domain.repository.comments.CommentsRepository
import com.andrewtsaturov.simplejsontesttask.domain.repository.comments.ICommentsRepository
import com.andrewtsaturov.simplejsontesttask.domain.repository.photos.IPhotosRepository
import com.andrewtsaturov.simplejsontesttask.domain.repository.photos.PhotosRepository
import com.andrewtsaturov.simplejsontesttask.domain.repository.posts.IPostsRepository
import com.andrewtsaturov.simplejsontesttask.domain.repository.posts.PostRepository
import com.andrewtsaturov.simplejsontesttask.presentation.common.ISchedulers
import com.andrewtsaturov.simplejsontesttask.presentation.common.Schedulers
import com.andrewtsaturov.simplejsontesttask.presentation.navigation.LocalCiceroneHolder
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.bottom_nav.BottomNavPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.bottom_nav.PostTabPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.posts.PostsPresenter
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.ConnectionSpec
import java.util.Arrays.asList
import android.os.Build
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.albums.AlbumsPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.bottom_nav.AlbumsTabPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.comments.CommentsPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.photos.PhotosPresenter
import java.util.*


val app = module {
    single { LocalCiceroneHolder() }

    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        var tlsSpecs = listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            tlsSpecs = listOf(ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT)
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectionSpecs(tlsSpecs)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .client(get())
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .build()
    }

    single {Schedulers() as ISchedulers}

    single {get<Retrofit>().create(PostsService::class.java)}
    single {PostsCache() as IPostsCache}
    single { PostRepository(get(), get()) as IPostsRepository}
    single { PostsInteractor(get()) as IPostsInteractor}

    single {get<Retrofit>().create(CommentsService::class.java)}
    single {CommentsCache() as ICommentCache}
    single { CommentsRepository(get(), get(), get()) as ICommentsRepository}
    single { CommentsInteractor(get()) as ICommentsInteractor}

    single {get<Retrofit>().create(AlbumsService::class.java)}
    single {AlbumsCache() as IAlbumsCache}
    single { AlbumsRepository(get(), get()) as IAlbumsRepository}
    single { AlbumsInteractor(get()) as IAlbumsInteractor}

    single {get<Retrofit>().create(PhotosService::class.java)}
    single {PhotosCache() as IPhotosCache}
    single { PhotosRepository(get(), get(), get()) as IPhotosRepository}
    single { PhotosInteractor(get()) as IPhotosInteractor}

    factory { BottomNavPresenter() }
    factory { PostTabPresenter(get<LocalCiceroneHolder>().postsRouter) }
    factory { PostsPresenter(get<LocalCiceroneHolder>().postsRouter, get(), get()) }
    factory { CommentsPresenter(get<LocalCiceroneHolder>().postsRouter, get(), get())}

    factory { AlbumsTabPresenter(get<LocalCiceroneHolder>().albumsRouter) }
    factory { AlbumsPresenter(get<LocalCiceroneHolder>().albumsRouter, get(), get()) }
    factory { PhotosPresenter(get<LocalCiceroneHolder>().albumsRouter, get(), get())}
}