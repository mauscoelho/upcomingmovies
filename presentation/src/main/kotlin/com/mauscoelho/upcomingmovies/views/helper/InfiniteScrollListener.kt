package com.mauscoelho.upcomingmovies.views.helper

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView


class InfiniteScrollListener(
        val func: () -> Unit,
        val layoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            val visibleThreshold = 2
            val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
            val currentTotalCount = layoutManager.itemCount

            if (currentTotalCount <= lastItem + visibleThreshold) {
                func()
            }
        }
    }

}