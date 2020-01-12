package com.andrewtsaturov.simplejsontesttask.presentation.presenter.comments

import com.andrewtsaturov.simplejsontesttask.domain.interactor.comments.ICommentsInteractor
import com.andrewtsaturov.simplejsontesttask.presentation.common.ISchedulers
import com.andrewtsaturov.simplejsontesttask.presentation.view.CommentsView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import ru.terrakok.cicerone.Router

@InjectViewState
class CommentsPresenter(
    private val router: Router,
    private val schedulers: ISchedulers,
    private val commentsIteractor: ICommentsInteractor
    ): MvpPresenter<CommentsView>() {
    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        commentsIteractor.observePostForReading()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                viewState.showPost(it)
            }, {
                it.printStackTrace()
            })
            .untilDestroy()

        commentsIteractor.observeComments()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                viewState.showComments(it)
            }, {
                it.printStackTrace()
            })
            .untilDestroy()


    }

    fun Disposable.untilDestroy() {
        disposable += this
    }
}