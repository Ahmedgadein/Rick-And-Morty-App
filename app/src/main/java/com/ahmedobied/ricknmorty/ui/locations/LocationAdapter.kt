package com.ahmedobied.ricknmorty.ui.locations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmedobied.ricknmorty.R
import com.ahmedobied.ricknmorty.data.db.entities.LocationEntity
import com.ahmedobied.ricknmorty.generic.adapter.GenericRecyclerviewAdapter
import com.ahmedobied.ricknmorty.generic.listener.OnRecyclerObjectClickListener
import com.ahmedobied.ricknmorty.generic.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_location_list.view.*

class LocationAdapter() :
    GenericRecyclerviewAdapter<LocationEntity, OnRecyclerObjectClickListener<LocationEntity>, LocationAdapter.LocationHolder>() {
    override fun onCreateViewHolder(view: ViewGroup, postition: Int): LocationHolder {
        return LocationHolder(
            LayoutInflater.from(view.context.applicationContext)
                .inflate(R.layout.item_location_list, view, false)
        )
    }

    class LocationHolder(itemView: View) :
        BaseViewHolder<LocationEntity, OnRecyclerObjectClickListener<LocationEntity>>(itemView) {
        private lateinit var location: LocationEntity
        override fun onBind(
            item: LocationEntity,
            listener: OnRecyclerObjectClickListener<LocationEntity>?
        ) {
            location = item

            itemView.apply {
                location_title.text = location.name
                location_description.text = location.type
            }

            if (listener != null) {
                itemView.setOnClickListener {
                    listener.onItemClicked(location)
                }
            }
        }

    }
}