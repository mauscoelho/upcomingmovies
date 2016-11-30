package com.mauscoelho.upcomingmovies.views.helper

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView


class InfiniteScrollListener(
        val func: () -> Unit,
        val layoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {

    var previousTotal = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
            val currentTotalCount = layoutManager.itemCount

            if ((currentTotalCount - 4 == lastItem + 1) && previousTotal != currentTotalCount ) {
                previousTotal = currentTotalCount
                func()
            }
        }
    }

}