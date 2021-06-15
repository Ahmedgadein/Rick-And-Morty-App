package com.ahmedobied.ricknmorty.ui.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmedobied.ricknmorty.R
import com.ahmedobied.ricknmorty.data.db.entities.EpisodeEntity
import com.ahmedobied.ricknmorty.generic.adapter.GenericRecyclerviewAdapter
import com.ahmedobied.ricknmorty.generic.listener.OnRecyclerObjectClickListener
import com.ahmedobied.ricknmorty.generic.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_episode_list.view.*

class EpisodeAdapter :
    GenericRecyclerviewAdapter<EpisodeEntity, OnRecyclerObjectClickListener<EpisodeEntity>, EpisodeAdapter.EpisodeHolder>() {

    override fun onCreateViewHolder(view: ViewGroup, postition: Int): EpisodeHolder =
        EpisodeHolder(
            LayoutInflater.from(view.context).inflate(R.layout.item_episode_list, view, false)
        )

    class EpisodeHolder(itemView: View) :
        BaseViewHolder<EpisodeEntity, OnRecyclerObjectClickListener<EpisodeEntity>>(itemView) {
        private lateinit var episode: EpisodeEntity
        override fun onBind(
            item: EpisodeEntity,
            listener: OnRecyclerObjectClickListener<EpisodeEntity>?
        ) {
            episode = item

            itemView.episode_title.text = episode.episode
            itemView.episode_name.text = episode.name

            if (listener != null)
                itemView.setOnClickListener {
                    listener.onItemClicked(episode)
                }
        }

    }
}