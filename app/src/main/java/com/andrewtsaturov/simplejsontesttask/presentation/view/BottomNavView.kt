package com.andrewtsaturov.simplejsontesttask.presentation.view

import com.arellomobile.mvp.MvpView

interface BottomNavView: MvpView {
    fun showTab(tabInt: Int)
}