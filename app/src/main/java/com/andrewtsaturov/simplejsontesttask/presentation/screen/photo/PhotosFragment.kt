package com.andrewtsaturov.simplejsontesttask.presentation.screen.photo

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Album
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Photo
import com.andrewtsaturov.simplejsontesttask.presentation.common.BaseFragment
import com.andrewtsaturov.simplejsontesttask.presentation.common.GridSpacingItemDecoration
import com.andrewtsaturov.simplejsontesttask.presentation.common.OnBackPressed
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.photos.PhotosPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.screen.photo.adapter.PhotoItem
import com.andrewtsaturov.simplejsontesttask.presentation.view.PhotosView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_album.*
import org.koin.android.ext.android.get
import com.stfalcon.frescoimageviewer.ImageViewer



class PhotosFragment(): BaseFragment(), PhotosView, OnPhotoClickListener, OnBackPressed{

    companion object {
        private const val ALBUM_ID_KEY = "ALBUM_ID"
        fun newInstanse(albumID: Long) = PhotosFragment().apply {
            arguments = Bundle().apply { putLong(ALBUM_ID_KEY, albumID) }
        }
    }

    override val layoutResource: Int = R.layout.fragment_album

    @InjectPresenter
    lateinit var presenter: PhotosPresenter

    @ProvidePresenter
    fun providePresenter(): PhotosPresenter = get()

    private val photosAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewAlbum.apply {
            layoutManager = GridLayoutManager(context, 3)
            if (itemDecorationCount == 0)
                addItemDecoration(
                    GridSpacingItemDecoration(
                        2,
                        TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            9.0f,
                            context.resources.displayMetrics
                        ).toInt(),
                        false,
                        0
                    )
                )
            adapter = photosAdapter

        }

        toolbarAlbum.setNavigationOnClickListener { presenter.back() }
    }

    override fun onStart() {
        super.onStart()

        presenter.loadData(getPostID() ?: 0)
    }

    override fun showAlbum(album: Album) {
        toolbarAlbum.title = album.name
    }

    override fun showPhotos(photos: List<Photo>) {
        photosAdapter.clear()
        photosAdapter.addAll(photos.map { PhotoItem(it, this@PhotosFragment) })
    }

    override fun showFullImage(url: String) {
        ImageViewer.Builder(context, listOf(url))
            .setStartPosition(0)
            .show()
    }

    override fun onPhotoClicked(imageUrl: String) = presenter.photosClicked(imageUrl)

    override fun onBackPressed() = presenter.back()

    private fun getPostID() = arguments?.getLong(ALBUM_ID_KEY)
}