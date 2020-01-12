package com.andrewtsaturov.simplejsontesttask.presentation.presenter.albums

import com.andrewtsaturov.simplejsontesttask.domain.interactor.albums.AlbumsInteractor
import com.andrewtsaturov.simplejsontesttask.presentation.common.ISchedulers
import com.andrewtsaturov.simplejsontesttask.presentation.navigation.Screens
import com.andrewtsaturov.simplejsontesttask.presentation.view.AlbumsView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import ru.terrakok.cicerone.Router

@InjectViewState
class AlbumsPresenter(
    private val router: Router,
    private val schedulers: ISchedulers,
    private val albumsInteractor: AlbumsInteractor
): MvpPresenter<AlbumsView>() {
    private val disposable = CompositeDisposable()

    private var pageCounter = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadNextPage()

        albumsInteractor.observeAlbums()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                viewState.updateAlbums(it)
            }, {
                it.printStackTrace()
            })
            .untilDestroy()
    }

    fun loadNextPage(){
        albumsInteractor.loadAlbumsPage(pageCounter)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .doOnSuccess {
                pageCounter += 1
            }
            .subscribe({}, {
                it.printStackTrace()
            })
            .untilDestroy()
    }

    fun onAlbumClicked(id: Long){
        router.navigateTo(Screens.PhotosScreen(id))
    }

    fun Disposable.untilDestroy() {
        disposable += this
    }
}