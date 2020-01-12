package com.andrewtsaturov.simplejsontesttask.presentation.screen.comments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrewtsaturov.simplejsontesttask.R
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Comment
import com.andrewtsaturov.simplejsontesttask.domain.etnity.Post
import com.andrewtsaturov.simplejsontesttask.presentation.common.BaseFragment
import com.andrewtsaturov.simplejsontesttask.presentation.common.OnBackPressed
import com.andrewtsaturov.simplejsontesttask.presentation.presenter.comments.CommentsPresenter
import com.andrewtsaturov.simplejsontesttask.presentation.screen.comments.adapter.CommentsItem
import com.andrewtsaturov.simplejsontesttask.presentation.view.CommentsView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_post_comments.*
import org.koin.android.ext.android.get

class CommentsFragment: BaseFragment(), CommentsView, OnBackPressed {

    companion object {
        private const val POST_ID_KEY = "POST_ID"
        fun newInstanse(postID: Long) = CommentsFragment().apply {
            arguments = Bundle().apply { putLong(POST_ID_KEY, postID) }
        }
    }

    override val layoutResource: Int = R.layout.fragment_post_comments

    @InjectPresenter
    lateinit var presenter: CommentsPresenter

    @ProvidePresenter
    fun providePresenter(): CommentsPresenter = get()

    private val commentAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarPostComments.setNavigationOnClickListener { presenter.back() }
        recyclerViewComments.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            adapter = commentAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.loadData(getPostID()?: 0)
    }

    override fun showPost(post: Post) {
        toolbarPostComments.title = post.header
        postHeaderTxt.text = post.header
        postBodyTxt.text = post.body
    }

    override fun showComments(comments: List<Comment>) {
        commentAdapter.clear()
        commentAdapter.addAll(comments.map { CommentsItem(it) })
    }

    override fun onBackPressed() = presenter.back()

    private fun getPostID() = arguments?.getLong(POST_ID_KEY)
}