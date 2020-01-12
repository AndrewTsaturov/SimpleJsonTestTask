package com.andrewtsaturov.simplejsontesttask.presentation.screen.bottom_nav

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.presentation.common.BaseFragment
import com.andrewtsaturov.simplejsontesttask.presentation.common.OnBackPressed
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.bottom_nav.BottomNavPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.screen.MainActivity
import com.andrewtsaturov.simplejsontesttask.presentation.screen.bottom_nav.adapter.TabAdapter
import com.andrewtsaturov.simplejsontesttask.presentation.view.BottomNavView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*
import org.koin.android.ext.android.get

class BottomNavigationFragment: BaseFragment(), BottomNavView, BottomNavigationView.OnNavigationItemSelectedListener, OnBackPressed {
    override val layoutResource: Int = R.layout.fragment_bottom_navigation

    @InjectPresenter
    lateinit var presenter: BottomNavPresenter

    @ProvidePresenter
    fun providePresenter(): BottomNavPresenter = get()

    private var pagerAdapter: TabAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerAdapter = TabAdapter(childFragmentManager, 0)
        bottomNavigationViewPager.apply {
            isPagingEnabled = false
            adapter = pagerAdapter
        }

        bottomNavBar.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean = when(menuItem.itemId){
        R.id.nav_posts -> {
            presenter.bottomTabClicked(0)
            true
        }
        R.id.nav_albums -> {
            presenter.bottomTabClicked(1)
            true
        }

        else -> false
    }

    override fun showTab(tabInt: Int) {
        bottomNavigationViewPager.currentItem = tabInt
    }

    override fun onBackPressed() {
        (pagerAdapter?.getItem(bottomNavigationViewPager.currentItem) as? OnBackPressed)?.onBackPressed() ?: (activity as MainActivity).back()
    }
}