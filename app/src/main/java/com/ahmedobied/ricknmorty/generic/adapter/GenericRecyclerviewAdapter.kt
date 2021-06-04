package com.ahmedobied.ricknmorty.generic.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedobied.ricknmorty.generic.listener.BaseRecyclerListener
import com.ahmedobied.ricknmorty.generic.viewholder.BaseViewHolder

abstract class GenericRecyclerviewAdapter<T, L : BaseRecyclerListener, VH : BaseViewHolder<T, L>>(private var items:List<T> = emptyList()) :
    RecyclerView.Adapter<VH>() {
    private lateinit var listener: L

    fun setListener(listener: L) {
        this.listener = listener
    }

    fun updateItems(items: List<T>){
        this.items = items
        notifyDataSetChanged()
    }

    abstract override fun onCreateViewHolder(view: ViewGroup, postition: Int): VH

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        var item = items[position]
        holder.onBind(item, listener)
    }
}