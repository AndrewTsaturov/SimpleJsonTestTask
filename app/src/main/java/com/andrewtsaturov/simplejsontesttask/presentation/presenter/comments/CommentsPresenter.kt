package com.andrewtsaturov.simplejsontesttask.presentation.presenter.comments

import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
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

    private var post: Post? = null

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

    fun back() {
        router.exit()
    }

    fun loadData(postID: Long) {
        if(post == null)
            commentsIteractor.getPostById(postID)
            .subscribeOn(schedulers.io())
            .doOnSuccess {
                commentsIteractor
                    .getCommtensByPostId(postID)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({}, {
                        it.printStackTrace()
                    })
                    .untilDestroy()
            }.observeOn(schedulers.ui())
            .subscribe({
                post = it
            }, {
                it.printStackTrace()
            })
            .untilDestroy()
    }
}