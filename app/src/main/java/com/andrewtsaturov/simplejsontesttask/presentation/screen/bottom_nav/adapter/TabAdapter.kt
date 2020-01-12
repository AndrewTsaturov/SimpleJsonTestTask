package com.andrewtsaturov.simplejsontesttask.presentation.screen.bottom_nav.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.andrewtsaturov.simplejsontesttask.presentation.screen.bottom_nav.tab_fragments.AlbumsRootFragment
import com.andrewtsaturov.simplejsontesttask.presentation.screen.bottom_nav.tab_fragments.PostRootFragment

class TabAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {

    private val albums = AlbumsRootFragment()
    private val posts = PostRootFragment()

    override fun getItem(position: Int): Fragment = when(position){
        0 -> posts
        1 -> albums
        else -> posts
    }

    override fun getCount(): Int = 2
}