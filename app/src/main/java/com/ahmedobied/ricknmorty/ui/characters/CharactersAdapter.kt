package com.ahmedobied.ricknmorty.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmedobied.ricknmorty.R
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity
import com.ahmedobied.ricknmorty.generic.adapter.GenericRecyclerviewAdapter
import com.ahmedobied.ricknmorty.generic.listener.OnRecyclerObjectClickListener
import com.ahmedobied.ricknmorty.generic.viewholder.BaseViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_character_list.view.*

class CharactersAdapter() :
    GenericRecyclerviewAdapter<CharacterEntity, OnRecyclerObjectClickListener<CharacterEntity>, CharactersAdapter.CharacterHolder>() {

    override fun onCreateViewHolder(view: ViewGroup, postition: Int): CharacterHolder =
        CharacterHolder(
            LayoutInflater.from(view.context).inflate(R.layout.item_character_list, view, false)
        )

    class CharacterHolder(itemView: View) :
        BaseViewHolder<CharacterEntity, OnRecyclerObjectClickListener<CharacterEntity>>(itemView) {
        private lateinit var character: CharacterEntity
        override fun onBind(
            item: CharacterEntity,
            listener: OnRecyclerObjectClickListener<CharacterEntity>?
        ) {
            character = item

            itemView.apply {
                Glide.with(itemView.context).load(item.imageUrl).into(itemView.character_image)
                character_name.text = character.name
                character_species.text = character.species
                character_gender.text = character.gender
                character_alive.text = character.status
            }

            if (listener != null) {
                itemView.setOnClickListener {
                    listener.onItemClicked(character)
                }
            }
        }
    }
}
