package com.andrewtsaturov.simplejsontesttask.presentation.presenter.posts

import com.andrewtsaturov.simplejsontesttask.domain.interactor.posts.IPostsInteractor
import com.andrewtsaturov.simplejsontesttask.presentation.common.ISchedulers
import com.andrewtsaturov.simplejsontesttask.presentation.navigation.Screens
import com.andrewtsaturov.simplejsontesttask.presentation.view.PostsView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import ru.terrakok.cicerone.Router

@InjectViewState
class PostsPresenter(
    private val router: Router,
    private val schedulers: ISchedulers,
    private val postsInteractor: IPostsInteractor
): MvpPresenter<PostsView>() {
    private val disposable = CompositeDisposable()

    private var pageCounter = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadNextPage()

        postsInteractor.observePosts()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                viewState.updatePosts(it)
            }, {
                it.printStackTrace()
            })
            .untilDestroy()
    }

    fun loadNextPage(){
        postsInteractor.loadPostPage(pageCounter)
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

    fun onPostClicked(id: Long){
        router.navigateTo(Screens.CommentsScreen(id))
    }

    fun Disposable.untilDestroy() {
        disposable += this
    }
}