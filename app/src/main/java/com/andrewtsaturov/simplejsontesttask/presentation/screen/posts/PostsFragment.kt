package com.andrewtsaturov.simplejsontesttask.presentation.screen.posts

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import com.andrewtsaturov.simplejsontesttask.presentation.common.BaseFragment
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.posts.PostsPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.screen.posts.adapter.PostItem
import com.andrewtsaturov.simplejsontesttask.presentation.view.PostsView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.android.ext.android.get

class PostsFragment: BaseFragment(), PostsView, OnPostClickListener {
    override val layoutResource: Int = R.layout.fragment_posts

    @InjectPresenter
    lateinit var presenter: PostsPresenter

    @ProvidePresenter
    fun providePresenter(): PostsPresenter = get()

    private val postAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewPosts.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun updatePosts(posts: List<Post>) {
        postAdapter.update(posts.map { PostItem(it, this@PostsFragment) })
    }

    override fun onPostClick(postId: Long) {
        presenter.onPostClicked(postId)
    }
}