package com.andrewtsaturov.simplejsontesttask.presentation.presenter.bottom_nav

import com.andrewtsaturov.simplejsontesttask.presentation.view.BottomNavView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class BottomNavPresenter(): MvpPresenter<BottomNavView>() {
    fun bottomTabClicked(position: Int){
        viewState.showTab(position)
    }
}