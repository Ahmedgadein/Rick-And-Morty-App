package com.ahmedobied.ricknmorty.generic.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ahmedobied.ricknmorty.generic.listener.BaseRecyclerListener

abstract class BaseViewHolder<T, L : BaseRecyclerListener>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(item:T, listener:L?)
}
