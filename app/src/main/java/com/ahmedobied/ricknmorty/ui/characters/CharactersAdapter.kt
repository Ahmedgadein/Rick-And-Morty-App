package com.ahmedobied.ricknmorty.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedobied.ricknmorty.R
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_character_list.view.*

class CharactersAdapter(
    private var characters: List<CharacterEntity> = ArrayList(),
    private val onClick: (CharacterEntity) -> Any
) :
    RecyclerView.Adapter<CharactersAdapter.CharacterHolder>() {
    class CharacterHolder(itemView: View, onClick: (CharacterEntity) -> Any) : RecyclerView.ViewHolder(itemView) {
        private var currentCharacter: CharacterEntity? = null

        init {
            itemView.setOnClickListener{
                currentCharacter?.let {
                    onClick(it)
                }
            }
        }

        fun bind(character: CharacterEntity) {
            currentCharacter = character
            itemView.apply {
                Glide.with(itemView.context).load(character.imageUrl).into(itemView.character_image)
                character_name.text = character.name
                character_species.text = character.species
                character_gender.text = character.gender
                character_alive.text = character.status
            }
        }

    }

    fun updateCharacters(newList: List<CharacterEntity>) {
        characters = newList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character_list, parent, false)
        return CharacterHolder(view, onClick)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) =
        holder.bind(characters[position])
}