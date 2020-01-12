package com.andrewtsaturov.simplejsontesttask.presentation.screen.bottom_nav.tab_fragments

import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.presentation.common.BaseFragment
import com.andrewtsaturov.simplejsontesttask.presentation.common.OnBackPressed
import com.andrewtsaturov.simplejsontesttask.presentation.navigation.LocalCiceroneHolder
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.bottom_nav.PostTabPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.screen.MainActivity
import com.andrewtsaturov.simplejsontesttask.presentation.view.PostTabView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class PostRootFragment: BaseFragment(), PostTabView, OnBackPressed {
    override val layoutResource: Int = R.layout.fragment_root_tab_post

    private val navigatorHolder = get<LocalCiceroneHolder>().postsHolder
    private val navigator by lazy { SupportAppNavigator(activity, childFragmentManager, R.id.post_tab_fragment_container) }
    private val router = get<LocalCiceroneHolder>().postsRouter

    @InjectPresenter
    lateinit var presenter: PostTabPresenter

    @ProvidePresenter
    fun providePresenter(): PostTabPresenter = get()

    override fun onResume() {
        super.onResume()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        (childFragmentManager.findFragmentById(R.id.post_tab_fragment_container) as? OnBackPressed)?.onBackPressed() ?: (activity as MainActivity).back()
    }
}