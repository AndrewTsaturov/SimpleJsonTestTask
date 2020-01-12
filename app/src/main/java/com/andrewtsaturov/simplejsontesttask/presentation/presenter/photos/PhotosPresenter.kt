package com.andrewtsaturov.simplejsontesttask.presentation.presenter.photos

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.interactor.photos.IPhotosInteractor
import com.andrewtsaturov.simplejsontesttask.presentation.common.ISchedulers
import com.andrewtsaturov.simplejsontesttask.presentation.view.PhotosView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import ru.terrakok.cicerone.Router

@InjectViewState
class PhotosPresenter(
    private val router: Router,
    private val schedulers: ISchedulers,
    private val photosInteractor: IPhotosInteractor
    ): MvpPresenter<PhotosView>() {
    private val disposable = CompositeDisposable()

    private var album: Album? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        photosInteractor.observeAlbum()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                viewState.showAlbum(it)
            }, {
                it.printStackTrace()
            })
            .untilDestroy()

        photosInteractor.observePhotos()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                viewState.showPhotos(it)
            }, {
                it.printStackTrace()
            })
            .untilDestroy()


    }

    fun Disposable.untilDestroy() {
        disposable += this
    }

    fun back() {
        router.exit()
    }

    fun loadData(postID: Long) {
        if(album == null)
            photosInteractor.getAlbomById(postID)
            .subscribeOn(schedulers.io())
            .doOnSuccess {
                photosInteractor
                    .getPhotoByPostId(postID)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({}, {
                        it.printStackTrace()
                    })
                    .untilDestroy()
            }.observeOn(schedulers.ui())
            .subscribe({
                album = it
            }, {
                it.printStackTrace()
            })
            .untilDestroy()
    }

    fun photosClicked(url: String){
        viewState.showFullImage(url)
    }
}