package com.ahmedobied.ricknmorty.generic.listener

interface OnRecyclerObjectClickListener<T> :
    BaseRecyclerListener {
    fun onItemClicked(item: T)
}