package com.ahmedobied.ricknmorty.ui.locations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmedobied.ricknmorty.R
import com.ahmedobied.ricknmorty.data.db.entities.LocationEntity
import com.ahmedobied.ricknmorty.generic.adapter.GenericRecyclerviewAdapter
import com.ahmedobied.ricknmorty.generic.listener.OnRecyclerObjectClickListener
import com.ahmedobied.ricknmorty.generic.viewholder.BaseViewHolder

class LocationAdapter() : GenericRecyclerviewAdapter<LocationEntity, OnRecyclerObjectClickListener<LocationEntity>, LocationAdapter.LocationHolder>(){
    override fun onCreateViewHolder(view: ViewGroup, postition: Int): LocationHolder{
        return LocationHolder(
            LayoutInflater.from(view.context.applicationContext).inflate(R.layout.item_character_list,view,false)
        )
    }
    class LocationHolder(itemView:View): BaseViewHolder<LocationEntity,OnRecyclerObjectClickListener<LocationEntity>>(itemView){
        override fun onBind(
            item: LocationEntity,
            listener: OnRecyclerObjectClickListener<LocationEntity>?
        ) {

        }

    }
}