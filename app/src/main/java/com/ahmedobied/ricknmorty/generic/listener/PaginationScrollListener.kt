package com.ahmedobied.ricknmorty.generic.listener

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class PaginationScrollListener(var layoutManager: GridLayoutManager) :
    RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleThreshold = 2

        val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
        val currentTotalCount = layoutManager.itemCount

        if (currentTotalCount <= lastItem + visibleThreshold) {
            loadMoreItems()
        }
    }

    protected abstract fun loadMoreItems()
}