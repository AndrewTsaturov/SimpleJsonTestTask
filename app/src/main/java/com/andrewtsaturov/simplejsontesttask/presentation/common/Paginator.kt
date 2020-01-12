package com.andrewtsaturov.simplejsontesttask.presentation.common

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


class Paginator(private val onAllScrolledListener: OnAllScrolledListener, private val layoutManager: RecyclerView.LayoutManager): RecyclerView.OnScrollListener() {
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true


    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        if (onAllScrolledListener != null) {
            var lastVisibleItemPosition = 0
            val totalItemCount = layoutManager.itemCount

            if (layoutManager is StaggeredGridLayoutManager) {
                val lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null)
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            } else if (layoutManager is LinearLayoutManager) {
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            } else if (layoutManager is GridLayoutManager) {
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            }

            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = 0
                this.previousTotalItemCount = totalItemCount
                if (totalItemCount == 0) {
                    this.loading = true
                }
            }

            if (loading && totalItemCount > previousTotalItemCount) {
                loading = false
                previousTotalItemCount = totalItemCount
            }

            val visibleThreshold = 5
            if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
                currentPage++
                //loadMoreListener.onLoadMore(currentPage, totalItemCount)
                onAllScrolledListener.onScrolled()
                loading = true
            }
        }
    }
}