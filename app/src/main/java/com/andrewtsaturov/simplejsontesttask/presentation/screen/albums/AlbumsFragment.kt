package com.andrewtsaturov.simplejsontesttask.presentation.screen.albums

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.presentation.common.BaseFragment
import com.andrewtsaturov.simplejsontesttask.presentation.common.OnAllScrolledListener
import com.andrewtsaturov.simplejsontesttask.presentation.common.Paginator
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.albums.AlbumsPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.screen.albums.adapter.AlbumItem
import com.andrewtsaturov.simplejsontesttask.presentation.view.AlbumsView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_albums.*
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.android.ext.android.get

class AlbumsFragment: BaseFragment(), AlbumsView, OnAlbumsClickListener {
    override val layoutResource: Int = R.layout.fragment_albums

    @InjectPresenter
    lateinit var presenter: AlbumsPresenter

    @ProvidePresenter
    fun providePresenter(): AlbumsPresenter = get()

    private val albumsAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewAlbums.apply {
            val manager = LinearLayoutManager(context)
            recyclerViewPosts.adapter = albumsAdapter
            layoutManager = manager
            addOnScrollListener(Paginator(object : OnAllScrolledListener {
                override fun onScrolled() {
                    presenter.loadNextPage()
                }
            }, manager))
        }
    }

    override fun updateAlbums(albums: List<Album>) {
        albumsAdapter.clear()
        albumsAdapter.addAll(albums.map { AlbumItem(it, this@AlbumsFragment) })
    }

    override fun onAlbumClick(albumID: Long) {
        presenter.onAlbumClicked(albumID)
    }
}