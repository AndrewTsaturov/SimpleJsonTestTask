package com.andrewtsaturov.simplejsontesttask.presentation.screen.bottom_nav.tab_fragments

import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.presentation.common.BaseFragment
import com.andrewtsaturov.simplejsontesttask.presentation.common.OnBackPressed
import com.andrewtsaturov.simplejsontesttask.presentation.screen.MainActivity

class AlbumsRootFragment: BaseFragment(), OnBackPressed {
    override val layoutResource: Int = R.layout.fragment_root_tab_albums

    override fun onBackPressed() {
        (childFragmentManager.findFragmentById(R.id.albums_tab_fragment_container) as? OnBackPressed)?.onBackPressed() ?: (activity as MainActivity).back()
    }
}