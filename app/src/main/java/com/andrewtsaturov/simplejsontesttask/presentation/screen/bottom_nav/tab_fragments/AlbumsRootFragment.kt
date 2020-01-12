package com.andrewtsaturov.simplejsontesttask.presentation.screen.bottom_nav.tab_fragments

import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.presentation.common.BaseFragment
import com.andrewtsaturov.simplejsontesttask.presentation.common.OnBackPressed
import com.andrewtsaturov.simplejsontesttask.presentation.navigation.LocalCiceroneHolder
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.bottom_nav.AlbumsTabPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.screen.MainActivity
import com.andrewtsaturov.simplejsontesttask.presentation.view.AlbumsTabView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class AlbumsRootFragment: BaseFragment(), AlbumsTabView, OnBackPressed {
    override val layoutResource: Int = R.layout.fragment_root_tab_albums

    private val navigatorHolder = get<LocalCiceroneHolder>().albumsHolder
    private val navigator by lazy { SupportAppNavigator(activity, childFragmentManager, R.id.albums_tab_fragment_container) }
    private val router = get<LocalCiceroneHolder>().albumsRouter

    @InjectPresenter
    lateinit var presenter: AlbumsTabPresenter

    @ProvidePresenter
    fun providePresenter(): AlbumsTabPresenter = get()

    override fun onResume() {
        super.onResume()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        (childFragmentManager.findFragmentById(R.id.albums_tab_fragment_container) as? OnBackPressed)?.onBackPressed() ?: (activity as MainActivity).back()
    }
}