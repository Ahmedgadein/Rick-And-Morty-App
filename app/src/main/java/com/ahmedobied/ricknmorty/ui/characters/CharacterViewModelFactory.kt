package com.ahmedobied.ricknmorty.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmedobied.ricknmorty.data.repository.character.CharacterRepository

@Suppress("UNCHECKED_CAST")
class CharacterViewModelFactory(private val characterRepository: CharacterRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterViewModel(characterRepository) as T
    }
}