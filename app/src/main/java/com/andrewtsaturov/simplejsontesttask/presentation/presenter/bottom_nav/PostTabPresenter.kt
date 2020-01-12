package com.andrewtsaturov.simplejsontesttask.presentation.presenter.bottom_nav

import com.andrewtsaturov.simplejsontesttask.presentation.navigation.Screens
import com.andrewtsaturov.simplejsontesttask.presentation.view.PostTabView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class PostTabPresenter(private val router: Router): MvpPresenter<PostTabView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.newRootScreen(Screens.PostScreen())
    }
}